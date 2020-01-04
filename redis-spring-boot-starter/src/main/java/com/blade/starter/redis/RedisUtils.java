package com.blade.starter.redis;

import com.blade.util.serializer.JdkSerializer;
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

    /**
     * redis set
     *
     * @param key key
     * @param o   obj
     */
    public void save(String key, Object o) {
        Jedis jedis = null;
        try {
            jedis = this.getJedis();
            jedis.set(JdkSerializer.serialize(key), JdkSerializer.serialize(o));
        } catch (Exception e) {
            LOGGER.error("redis save fail", e);
        } finally {
            this.close(jedis);
        }
    }

    /**
     * redis get
     *
     * @param key key
     * @return obj
     */
    public Object get(String key) {
        Jedis jedis = null;
        try {
            jedis = this.getJedis();
            byte[] bytes = jedis.get(this.serialize(key));
            return null == bytes ? null : this.deserialize(bytes);
        } catch (Exception e) {
            LOGGER.error("redis get fail", e);
        } finally {
            this.close(jedis);
        }
        return null;
    }

    /**
     * redis set
     *
     * @param key     key
     * @param o       obj
     * @param seconds expire time
     */
    public void save(String key, Object o, int seconds) {
        Jedis jedis = null;
        try {
            jedis = this.getJedis();
            jedis.set(this.serialize(key), this.serialize(o));
            jedis.expire(this.serialize(key), seconds);
        } catch (Exception e) {
            LOGGER.error("redis save fail", e);
        } finally {
            this.close(jedis);
        }
    }

    /**
     * redis del
     *
     * @param key key
     */
    public void delete(String key) {
        Jedis jedis = null;
        try {
            jedis = this.getJedis();
            jedis.del(this.serialize(key));
        } catch (Exception e) {
            LOGGER.error("redis del fail", e);
        } finally {
            this.close(jedis);
        }
    }

    /**
     * 加密
     *
     * @param obj serialize obj
     * @return byte[]
     * @throws Exception 异常
     */
    private byte[] serialize(Object obj) throws Exception {
        return JdkSerializer.serialize(obj);
    }

    /**
     * 解密
     *
     * @param bytes bytes
     * @return obj
     * @throws Exception 异常
     */
    private Object deserialize(byte[] bytes) throws Exception {
        return JdkSerializer.deserialize(bytes);
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
