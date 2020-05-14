package com.blade.starter.redis;

import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author blade
 * 2019/12/17 14:47
 */
@Component
public class RedisUtils {

    private static Logger LOGGER = LoggerFactory.getLogger(RedisUtils.class);

    @Autowired
    private RedissonClient redisson;

    /**
     * redis set
     *
     * @param key   key
     * @param value {@link T}
     */
    public <T> void save(String key, T value) {
        try {
            RBucket<T> bucket = redisson.getBucket(key);
            bucket.set(value);
        } catch (Exception e) {
            LOGGER.error("redis save fail", e);
        }
    }

    /**
     * redis get
     *
     * @param key key
     * @return {@link T}
     */
    public <T> T get(String key) {
        try {
            RBucket<T> bucket = redisson.getBucket(key);
            return bucket.get();
        } catch (Exception e) {
            LOGGER.error("redis get fail", e);
        }
        return null;
    }

    /**
     * redis set
     *
     * @param key     key
     * @param value   value
     * @param seconds expire time
     */
    public <T> void save(String key, T value, int seconds) {
        try {
            RBucket<T> bucket = redisson.getBucket(key);
            bucket.set(value, seconds, TimeUnit.SECONDS);
        } catch (Exception e) {
            LOGGER.error("redis save fail", e);
        }
    }

    /**
     * redis del
     *
     * @param key key
     */
    public boolean delete(String key) {
        try {
            return redisson.getBucket(key).delete();
        } catch (Exception e) {
            LOGGER.error("redis del fail", e);
        }
        return false;
    }
}
