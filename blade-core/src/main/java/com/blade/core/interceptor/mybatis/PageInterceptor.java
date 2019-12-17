package com.blade.core.interceptor.mybatis;

import com.blade.core.model.request.PageSearchDTO;
import com.blade.core.page.Page;
import com.blade.core.page.PageMethod;
import com.blade.util.ReflectUtil;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;
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

    protected Map<CacheKey, MappedStatement> msCountMap = new HashMap<>();
    private static final List<ResultMapping> EMPTY_RESULTMAPPING = new ArrayList<ResultMapping>(0);

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

        // 默认需要分页的条件，都必须是继承 PageSearchDTO ，并且在 Mapper.java 上用 @Param("searchDTO")
        if (metaObject.hasGetter("searchDTO")) {

            Map<String, Object> additionalParameters = (Map<String, Object>) parameter;

            Object object = additionalParameters.get("searchDTO");
            if (null != object && object instanceof PageSearchDTO) {

                LOGGER.debug("进行分页处理");

                // 分页操作
                PageSearchDTO searchDTO = (PageSearchDTO) object;

                String baseSql = boundSql.getSql();

                String limitSql = this.getLimitSql(baseSql, searchDTO.getPageSize(), searchDTO.getPageNumber());

                ReflectUtil.setFieldValue(boundSql, "sql", limitSql);

                List list = executor.query(ms, parameter, rowBounds, resultHandler, cacheKey, boundSql);
                Page page = PageMethod.getLocalPage();
                page.setRecordList(list);

                // 执行条数查询sql
                this.executeCountSql(ms, boundSql, parameter, additionalParameters, executor, resultHandler, page, baseSql);
                return page;
            } else {
                // 不分页的操作
                return invocation.proceed();
            }
        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

    private void executeCountSql(MappedStatement ms, BoundSql boundSql, Object parameter,
                                 Map<String, Object> additionalParameters, Executor executor,
                                 ResultHandler resultHandler, Page page, String baseSql) throws SQLException {

        String countSql = this.getCountSql(baseSql);

        BoundSql countBoundSql = new BoundSql(ms.getConfiguration(), countSql, boundSql.getParameterMappings(), parameter);
        //当使用动态 SQL 时，可能会产生临时的参数，这些参数需要手动设置到新的 BoundSql 中
        for (String key : additionalParameters.keySet()) {
            countBoundSql.setAdditionalParameter(key, additionalParameters.get(key));
        }
        //创建 count 查询的缓存 key
        CacheKey countKey = executor.createCacheKey(ms, parameter, RowBounds.DEFAULT, countBoundSql);
        countKey.update("_Count");
        MappedStatement countMs = msCountMap.get(countKey);
        if (countMs == null) {
            //根据当前的 ms 创建一个返回值为 Long 类型的 ms
            countMs = this.newCountMappedStatement(ms);
            msCountMap.put(countKey, countMs);
        }
        //执行 count 查询
        List countResultList = executor.query(countMs, parameter, RowBounds.DEFAULT, resultHandler, countKey, countBoundSql);
        page.setTotalCount((int) countResultList.get(0));
    }

    private MappedStatement newCountMappedStatement(MappedStatement ms) {
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId() + "_COUNT", ms.getSqlSource(), ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        if (ms.getKeyProperties() != null && ms.getKeyProperties().length != 0) {
            StringBuilder keyProperties = new StringBuilder();
            for (String keyProperty : ms.getKeyProperties()) {
                keyProperties.append(keyProperty).append(",");
            }
            keyProperties.delete(keyProperties.length() - 1, keyProperties.length());
            builder.keyProperty(keyProperties.toString());
        }
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        //count查询返回值int
        List<ResultMap> resultMaps = new ArrayList<ResultMap>();
        ResultMap resultMap = new ResultMap.Builder(ms.getConfiguration(), ms.getId(), Integer.class, EMPTY_RESULTMAPPING).build();
        resultMaps.add(resultMap);
        builder.resultMaps(resultMaps);
        builder.resultSetType(ms.getResultSetType());
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());

        return builder.build();
    }

    private String getCountSql(String listSql) {

        String tempSql = listSql;

        // 确认from 的位置
        int formIndex = tempSql.toLowerCase().indexOf("from".toLowerCase());

        return "select count(1) " + listSql.substring(formIndex);
    }

    private String getLimitSql(String listSql, int pageSize, int pageNumber) {
        int limit = (pageNumber - 1) * pageSize;
        int offset = pageSize;

        return listSql + " limit " + limit + ", " + offset;
    }
}
