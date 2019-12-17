package com.blade.starter.redis;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author blade
 * 2019/12/17 11:08
 */
@Configuration
@ConditionalOnClass(value = {Jedis.class, JedisPool.class})
@EnableConfigurationProperties(RedisProperties.class)
public class RedisAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public Jedis jedis(RedisProperties redisProperties) {

        if (0 == redisProperties.getTimeout()) {
            return new Jedis(redisProperties.getHost(), redisProperties.getPort());
        }else {
            return new Jedis(redisProperties.getHost(), redisProperties.getPort(), redisProperties.getTimeout());
        }
    }

    @Bean
    @ConditionalOnMissingBean
    public JedisPool jedisPool(RedisProperties redisProperties) {

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(100);
        jedisPoolConfig.setMaxWaitMillis(2000);

        JedisPool jedisPool = new JedisPool(jedisPoolConfig, redisProperties.getHost(), redisProperties.getPort(),
                redisProperties.getTimeout(), null, redisProperties.getDatabase());
        return jedisPool;
    }
}
