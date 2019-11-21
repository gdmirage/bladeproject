package com.blade.core.interceptor;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.transaction.Transaction;

import java.sql.SQLException;
import java.util.List;

/**
 * @author blade
 * 2019/11/21 15:06
 */
public class PageExecutor implements Executor {

    private Executor executor;

    public PageExecutor(Executor executor) {
        this.executor = executor;
    }

    @Override
    public int update(MappedStatement ms, Object parameter) throws SQLException {
        return executor.update(ms, parameter);
    }

    @Override
    public <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, CacheKey cacheKey, BoundSql boundSql) throws SQLException {
        return null;
    }

    @Override
    public <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler) throws SQLException {
        return null;
    }

    @Override
    public <E> Cursor<E> queryCursor(MappedStatement ms, Object parameter, RowBounds rowBounds) throws SQLException {
        return this.executor.queryCursor(ms, parameter, rowBounds);
    }

    @Override
    public List<BatchResult> flushStatements() throws SQLException {
        return this.executor.flushStatements();
    }

    @Override
    public void commit(boolean required) throws SQLException {
        this.executor.commit(required);
    }

    @Override
    public void rollback(boolean required) throws SQLException {
        this.executor.rollback(required);
    }

    @Override
    public CacheKey createCacheKey(MappedStatement ms, Object parameterObject, RowBounds rowBounds, BoundSql boundSql) {
        return this.executor.createCacheKey(ms, parameterObject, rowBounds, boundSql);
    }

    @Override
    public boolean isCached(MappedStatement ms, CacheKey key) {
        return this.executor.isCached(ms, key);
    }

    @Override
    public void clearLocalCache() {
        this.executor.clearLocalCache();
    }

    @Override
    public void deferLoad(MappedStatement ms, MetaObject resultObject, String property, CacheKey key, Class<?> targetType) {
        this.executor.deferLoad(ms, resultObject, property, key, targetType);
    }

    @Override
    public Transaction getTransaction() {
        return this.executor.getTransaction();
    }

    @Override
    public void close(boolean forceRollback) {
        this.executor.close(forceRollback);
    }

    @Override
    public boolean isClosed() {
        return this.executor.isClosed();
    }

    @Override
    public void setExecutorWrapper(Executor executor) {
        this.executor.setExecutorWrapper(executor);
    }
}
