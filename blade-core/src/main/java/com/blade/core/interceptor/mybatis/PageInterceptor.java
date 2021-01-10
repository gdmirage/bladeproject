package com.blade.core.interceptor.mybatis;

import com.blade.core.model.request.PageSearchDTO;
import com.blade.core.page.Page;
import com.blade.core.page.PageMethod;
import com.blade.util.ReflectUtil;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * mybatis 分页拦截器
 * 针对query方法进行拦截
 *
 * @author blade
 * 2019/11/21 9:15
 */
@Intercepts(
        {
                @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
        }
)
public class PageInterceptor implements Interceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(PageInterceptor.class);

    private static final String COUNT_ID = "Count";
    /**
     * 存储Mapper中的ID
     */
    private HashMap<String, String> mapStatement = new HashMap<>();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        Object parameter = args[1];
        RowBounds rowBounds = (RowBounds) args[2];
        ResultHandler resultHandler = (ResultHandler) args[3];
        Executor executor = (Executor) invocation.getTarget();
        CacheKey cacheKey;
        BoundSql boundSql;
        //由于逻辑关系，只会进入一次
        if (args.length == 4) {
            //4 个参数时
            boundSql = ms.getBoundSql(parameter);
            cacheKey = executor.createCacheKey(ms, parameter, rowBounds, boundSql);
        } else {
            //6 个参数时
            cacheKey = (CacheKey) args[4];
            boundSql = (BoundSql) args[5];
        }
        MetaObject metaObject = SystemMetaObject.forObject(parameter);

        // 默认需要分页的条件，都必须是继承 PageSearchDTO
        Object pageObject = this.getPageSearchDTO(parameter);

        if (null != pageObject) {
            LOGGER.debug("进行分页处理");
            Page page = PageMethod.getLocalPage();
            if (null == page) {
                // 说明没有调用 startPage 方法， 不分页
                LOGGER.debug("没有调用 PageHelper.startPage 方法，不分页");
                return invocation.proceed();
            }

            // 分页操作
            PageSearchDTO searchDTO = (PageSearchDTO) pageObject;

            String baseSql = boundSql.getSql();

            String limitSql = this.getLimitSql(baseSql, searchDTO.getPageSize(), searchDTO.getPageNumber());

            ReflectUtil.setFieldValue(boundSql, "sql", limitSql);

            List list = executor.query(ms, parameter, rowBounds, resultHandler, cacheKey, boundSql);

            page.setRecordList(list);

            // 执行条数查询sql
            this.executeCountMethod(ms, page, invocation);

            // 清除线程缓存
            PageMethod.clearPage();
            return page;
        } else {
            // 不分页的操作
            return invocation.proceed();
        }
    }

    private Object getPageSearchDTO(Object parameter) {
        if (parameter instanceof String) {
            return null;
        }

        if (parameter instanceof PageSearchDTO) {
            return parameter;
        }

        if (parameter instanceof Map) {
            Map<String, Object> additionalParameters = (Map<String, Object>) parameter;

            Object pageObject = null;
            for (Object value: additionalParameters.values()) {
                if (value instanceof PageSearchDTO) {
                    pageObject = value;
                    break;
                }
            }

            return pageObject;
        }

        return null;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

    private void executeCountMethod(MappedStatement ms, Page page, Invocation invocation) throws Exception {
        Configuration configuration = ms.getConfiguration();

        if (mapStatement.isEmpty()) {
            initStatementMap(configuration);
        }

        LOGGER.info(ms.getId());
        page.setTotalCount(this.getTotalCount(invocation, ms));
    }

    /**
     * 获取所有statement语句的名称
     *
     * @param configuration {@link Configuration}
     */
    private synchronized void initStatementMap(Configuration configuration) {
        if (!mapStatement.isEmpty()) {
            return;
        }
        Collection<String> statements = configuration.getMappedStatementNames();

        for (String element : statements) {
            mapStatement.put(element, element);
        }
    }

    /**
     * 获取总记录数
     *
     * @param invocation      {@link Invocation}
     * @param mappedStatement {@link MappedStatement}
     * @return totalCOunt
     * @throws Exception 异常
     */
    private int getTotalCount(Invocation invocation, MappedStatement mappedStatement) throws Exception {

        String countId = mappedStatement.getId() + COUNT_ID;
        int count = 0;
        if (mapStatement.containsKey(countId)) {
            // 优先查找能统计条数的sql
            List data = (List) exeQuery(invocation, mappedStatement.getConfiguration().getMappedStatement(countId));
            if (data.size() > 0) {
                count = Integer.parseInt(data.get(0).toString());
            }
        } else {
            throw new Exception("未找到" + countId + "语句,在请Mapper中定义!");
        }

        return count;
    }

    /**
     * 根据提供的语句执行查询操作
     *
     * @param invocation     {@link Invocation}
     * @param queryStatement {@link MappedStatement}
     * @return Object result
     * @throws Exception 异常
     */
    private Object exeQuery(Invocation invocation, MappedStatement queryStatement) throws Exception {
        Object[] args = invocation.getArgs();
        return invocation.getMethod().invoke(invocation.getTarget(), queryStatement, args[1], args[2], args[3]);
    }

    private String getLimitSql(String listSql, int pageSize, int pageNumber) {
        int limit = (pageNumber - 1) * pageSize;

        return listSql + " limit " + limit + ", " + pageSize;
    }
}
