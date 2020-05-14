package com.blade.starter.redis.redisson;

/**
 * TODO:
 *
 * @author Blade
 * @date 2020/4/29 15:27
 */
public class RedissonSingleProperties {
    private String host;

    private String port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getAddress() {
        return this.host + ":" + this.port;
    }
}
