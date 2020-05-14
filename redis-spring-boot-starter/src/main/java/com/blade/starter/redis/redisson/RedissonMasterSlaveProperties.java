package com.blade.starter.redis.redisson;

import com.alibaba.fastjson.JSON;
import com.blade.util.FastJsonUtils;

/**
 * TODO:
 *
 * @author Blade
 * @date 2020/4/29 17:44
 */
public class RedissonMasterSlaveProperties {

    /**
     * 节点
     */
    private String nodes;

    private int failedSlaveCheckInterval = 180000;

    private int failedSlaveReconnectionInterval = 3000;

    /**
     * （从节点连接池大小） 默认值：64
     */
    private int slaveConnectionPoolSize = 64;

    private int slaveConnectionMinimumIdleSize = 32;

    /**
     * (主节点连接池大小）默认值：64
     */
    private int masterConnectionPoolSize = 64;

    private int masterConnectionMinimumIdleSize = 32;

    private int subscriptionConnectionPoolSize = 50;

    private int subscriptionConnectionMinimumIdleSize = 1;

    public String getNodes() {
        return nodes;
    }

    public void setNodes(String nodes) {
        this.nodes = nodes;
    }

    public int getFailedSlaveCheckInterval() {
        return failedSlaveCheckInterval;
    }

    public void setFailedSlaveCheckInterval(int failedSlaveCheckInterval) {
        this.failedSlaveCheckInterval = failedSlaveCheckInterval;
    }

    public int getFailedSlaveReconnectionInterval() {
        return failedSlaveReconnectionInterval;
    }

    public void setFailedSlaveReconnectionInterval(int failedSlaveReconnectionInterval) {
        this.failedSlaveReconnectionInterval = failedSlaveReconnectionInterval;
    }

    public int getSlaveConnectionPoolSize() {
        return slaveConnectionPoolSize;
    }

    public void setSlaveConnectionPoolSize(int slaveConnectionPoolSize) {
        this.slaveConnectionPoolSize = slaveConnectionPoolSize;
    }

    public int getSlaveConnectionMinimumIdleSize() {
        return slaveConnectionMinimumIdleSize;
    }

    public void setSlaveConnectionMinimumIdleSize(int slaveConnectionMinimumIdleSize) {
        this.slaveConnectionMinimumIdleSize = slaveConnectionMinimumIdleSize;
    }

    public int getMasterConnectionPoolSize() {
        return masterConnectionPoolSize;
    }

    public void setMasterConnectionPoolSize(int masterConnectionPoolSize) {
        this.masterConnectionPoolSize = masterConnectionPoolSize;
    }

    public int getMasterConnectionMinimumIdleSize() {
        return masterConnectionMinimumIdleSize;
    }

    public void setMasterConnectionMinimumIdleSize(int masterConnectionMinimumIdleSize) {
        this.masterConnectionMinimumIdleSize = masterConnectionMinimumIdleSize;
    }

    public int getSubscriptionConnectionPoolSize() {
        return subscriptionConnectionPoolSize;
    }

    public void setSubscriptionConnectionPoolSize(int subscriptionConnectionPoolSize) {
        this.subscriptionConnectionPoolSize = subscriptionConnectionPoolSize;
    }

    public int getSubscriptionConnectionMinimumIdleSize() {
        return subscriptionConnectionMinimumIdleSize;
    }

    public void setSubscriptionConnectionMinimumIdleSize(int subscriptionConnectionMinimumIdleSize) {
        this.subscriptionConnectionMinimumIdleSize = subscriptionConnectionMinimumIdleSize;
    }

    @Override
    public String toString() {
        return FastJsonUtils.toJsonString(this);
    }
}
