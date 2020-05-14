package com.blade.starter.redis.redisson;

/**
 * TODO:
 *
 * @author Blade
 * @date 2020/4/29 15:31
 */
public class RedissonSentinelProperties extends RedissonMasterSlaveProperties {
    /**
     * 哨兵master 名称
     */
    private String master;

    /**
     * 哨兵配置
     */
    private boolean masterOnlyWrite;

    @Override
    public String toString() {
        return super.toString();
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public boolean isMasterOnlyWrite() {
        return masterOnlyWrite;
    }

    public void setMasterOnlyWrite(boolean masterOnlyWrite) {
        this.masterOnlyWrite = masterOnlyWrite;
    }
}
