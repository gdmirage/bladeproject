package com.blade.manager.system.common.service.impl;

import com.blade.manager.system.common.service.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author blade
 * 2019/9/19 10:31
 */
@Service("redisService")
public class RedisServiceImpl implements IRedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void saveCaptcha(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
        stringRedisTemplate.expire(key, 5, TimeUnit.MINUTES);
    }

    @Override
    public String getCaptcha(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }
}
