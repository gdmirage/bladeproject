package com.blade.starter.redis.lock;

import com.blade.core.lock.IDistributionLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * TODO:
 * redis 分布式锁
 *
 * @author Blade
 * @date 2020/5/14 10:05
 */
@Component
public class RedisLock implements IDistributionLock {

    @Autowired
    private RedissonClient redisson;

    @Override
    public void lock(String key) {
        RLock lock = redisson.getLock(key);
        lock.lock();
    }

    @Override
    public void unlock(String key) {
        RLock lock = redisson.getLock(key);
        lock.unlock();
    }

    @Override
    public boolean tryLock(String key, long expire) throws InterruptedException {
        RLock lock = redisson.getLock(key);
        return lock.tryLock(expire, TimeUnit.MILLISECONDS);
    }

    @Override
    public boolean tryLock(String key, long expire, long timeout) throws InterruptedException {
        RLock lock = redisson.getLock(key);
        return lock.tryLock(expire, timeout, TimeUnit.MILLISECONDS);
    }
}