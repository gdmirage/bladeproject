package com.blade.starter.redis.redisson;

import com.blade.util.FastJsonUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * TODO:
 *
 * @author Blade
 * @date 2020/4/29 15:21
 */
@ConfigurationProperties(prefix = "spring.redisson")
public class RedissonProperties {
    private int database;

    /**
     * 等待节点回复命令的时间。该时间从命令发送成功时开始计时
     */
    private int timeout;

    private String password;

    /**
     * redis模式 single | cluster | sentinel
     */
    private String mode;

    /**
     * 池的配置信息
     */
    private RedissonPoolProperties pool;

    /**
     * 单机配置信息
     */
    private RedissonSingleProperties single;

    /**
     * 集群配置
     */
    private RedissonClusterProperties cluster;

    /**
     * 哨兵配置
     */
    private RedissonSentinelProperties sentinel;

    @Override
    public String toString() {
        return FastJsonUtils.toJsonString(this);
    }

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public RedissonPoolProperties getPool() {
        return pool;
    }

    public void setPool(RedissonPoolProperties pool) {
        this.pool = pool;
    }

    public RedissonSingleProperties getSingle() {
        return single;
    }

    public void setSingle(RedissonSingleProperties single) {
        this.single = single;
    }

    public RedissonClusterProperties getCluster() {
        return cluster;
    }

    public void setCluster(RedissonClusterProperties cluster) {
        this.cluster = cluster;
    }

    public RedissonSentinelProperties getSentinel() {
        return sentinel;
    }

    public void setSentinel(RedissonSentinelProperties sentinel) {
        this.sentinel = sentinel;
    }
}
