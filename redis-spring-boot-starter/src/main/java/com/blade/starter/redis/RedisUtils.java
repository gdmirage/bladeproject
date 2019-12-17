package com.blade.starter.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author blade
 * 2019/12/17 14:47
 */
@Component
public class RedisUtils {
    @Autowired
    private JedisPool jedisPool;

    private static Logger LOGGER = LoggerFactory.getLogger(RedisUtils.class);

    public void save(String key, Object o) {
        Jedis jedis = null;
        try {
            jedis = this.getJedis();
            jedis.set(key, String.valueOf(o));
        } catch (Exception e) {
            LOGGER.error("redis save fail", e);
        } finally {
            this.close(jedis);
        }
    }

    private Jedis getJedis() {
        return this.jedisPool.getResource();
    }

    private void close(Jedis jedis) {
        if (null != jedis) {
            jedis.close();
        }
    }
}
