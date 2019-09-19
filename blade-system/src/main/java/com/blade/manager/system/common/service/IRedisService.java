package com.blade.manager.system.common.service;

/**
 * @author blade
 * 2019/9/19 10:31
 */
public interface IRedisService {

    void saveCaptcha(String key, String value);

    String getCaptcha(String key);

    void delete(String key);
}
