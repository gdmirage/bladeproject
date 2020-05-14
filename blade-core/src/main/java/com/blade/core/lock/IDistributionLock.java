package com.blade.core.lock;

/**
 * TODO:
 * 分布式锁接口
 *
 * @author Blade
 * @date 2020/4/15 10:06
 */
public interface IDistributionLock {

    /**
     * 加锁
     *
     * @param key 锁的key
     */
    void lock(String key);

    /**
     * 解锁
     *
     * @param key 锁的key
     */
    void unlock(String key);

    /**
     * 尝试加锁， 一直到获取锁为止
     *
     * @param key    锁的key
     * @param expire 时间  毫秒
     * @return true 成功  false 不成功
     */
    boolean tryLock(String key, long expire) throws InterruptedException;

    /**
     * 尝试加锁一段时间，时间到了自动停止
     *
     * @param key     锁的key
     * @param expire  时间  毫秒
     * @param timeout 超时时间  毫秒
     * @return true 成功  false 不成功
     * @throws InterruptedException 异常
     */
    boolean tryLock(String key, long expire, long timeout) throws InterruptedException;
}
