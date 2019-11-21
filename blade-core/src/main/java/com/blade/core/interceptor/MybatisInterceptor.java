package com.blade.core.interceptor;

import com.blade.core.model.Page;
import com.blade.core.model.request.PageSearchDTO;
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
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * mybatis 分页拦截器  未完成
 *
 * @author blade
 * 2019/11/21 9:15
 */
@Intercepts(
        {
                @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
        }
)
public class MybatisInterceptor implements Interceptor {
    protected Map<CacheKey, MappedStatement> msCountMap = new HashMap<>();
    private Field additionalParametersField;
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
        System.out.println("-------------------->sql=" + boundSql.getSql());
        System.out.println("-------------------->parameter=" + parameter);

        Map<String, Object> additionalParameters = (Map<String, Object>) parameter;

        PageSearchDTO searchDTO = (PageSearchDTO) additionalParameters.get("searchDTO");
        System.out.println("-------------------->searchDTO=" + searchDTO);
        System.out.println("-------------------->pageSize=" + searchDTO.getPageSize());
        System.out.println("-------------------->pageNumber=" + searchDTO.getPageNumber());

        String countSql = this.getCountSql(boundSql.getSql());
        System.out.println("-------------------->countSql=" + countSql);

        String limitSql = this.getLimitSql(boundSql.getSql(), searchDTO.getPageSize(), searchDTO.getPageNumber());

        System.out.println("-------------------->limitSql=" + limitSql);

        setFieldValue(boundSql, "sql", limitSql);

        List list = executor.query(ms, parameter, rowBounds, resultHandler, cacheKey, boundSql);
        System.out.println(list);
        Page page = new Page(1, 10);
        page.setRecordList(list);


        BoundSql countBoundSql = new BoundSql(ms.getConfiguration(), countSql, boundSql.getParameterMappings(), parameter);
        //当使用动态 SQL 时，可能会产生临时的参数，这些参数需要手动设置到新的 BoundSql 中
        for (String key : additionalParameters.keySet()) {
            countBoundSql.setAdditionalParameter(key, additionalParameters.get(key));
        }
        //创建 count 查询的缓存 key
        CacheKey countKey = executor.createCacheKey(ms, parameter, RowBounds.DEFAULT, boundSql);
        countKey.update("_Count");
        MappedStatement countMs = msCountMap.get(countKey);
        if (countMs == null) {
            //根据当前的 ms 创建一个返回值为 Long 类型的 ms
            countMs = newCountMappedStatement(ms);
            msCountMap.put(countKey, countMs);
        }
        //执行 count 查询
        List countResultList = executor.query(countMs, parameter, RowBounds.DEFAULT, resultHandler, countKey, countBoundSql);
        page.setTotalCount((int)countResultList.get(0));

        return page;
    }

    public static MappedStatement newCountMappedStatement(MappedStatement ms) {
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

    @Override
    public Object plugin(Object target) {
        if(Executor.class.isAssignableFrom(target.getClass())){
            PageExecutor pageExecutor = new PageExecutor((Executor) target);
            return Plugin.wrap(pageExecutor, this);
        }else {
            return Plugin.wrap(target, this);
        }
    }

    @Override
    public void setProperties(Properties properties) {
        System.out.println("-------------------->properties=" + properties);
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

    /**
     * 利用反射获取指定对象里面的指定属性
     *
     * @param obj       目标对象
     * @param fieldName 目标属性
     * @return 目标字段
     */
    private static Field getField(Object obj, String fieldName) {
        Field field = null;
        for (Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                break;
            } catch (NoSuchFieldException e) {                  //这里不用做处理，子类没有该字段可能对应的父类有，都没有就返回null。
            }
        }
        return field;
    }

    /**
     * 利用反射设置指定对象的指定属性为指定的值
     *
     * @param obj        目标对象
     * @param fieldName  目标属性
     * @param fieldValue 目标值
     */
    public static void setFieldValue(Object obj, String fieldName,
                                     String fieldValue) {
        Field field = MybatisInterceptor.getField(obj, fieldName);
        if (field != null) {
            try {
                field.setAccessible(true);
                field.set(obj, fieldValue);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
