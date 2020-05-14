package com.blade.starter.redis.redisson;

import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.config.ReadMode;
import org.redisson.config.SentinelServersConfig;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * TODO:
 * 单机redisson
 *
 * @author Blade
 * @date 2020/4/29 10:32
 */
@Configuration
@ConditionalOnClass({Redisson.class})
@EnableConfigurationProperties(RedissonProperties.class)
public class RedissonConfiguration {

    @Autowired
    private RedissonProperties redissonProperties;

    /**
     * 单机模式 redisson 客户端
     */

    @Bean
    @ConditionalOnProperty(name = "spring.redisson.mode", havingValue = "single")
    RedissonClient redissonSingle() {
        Config config = new Config();
        String node = redissonProperties.getSingle().getAddress();
        node = node.startsWith("redis://") ? node : "redis://" + node;
        SingleServerConfig serverConfig = config.useSingleServer()
                .setAddress(node)
                .setTimeout(redissonProperties.getPool().getConnTimeout())
                .setConnectionPoolSize(redissonProperties.getPool().getSize())
                .setConnectionMinimumIdleSize(redissonProperties.getPool().getMinIdle());
        if (StringUtils.isNotBlank(redissonProperties.getPassword())) {
            serverConfig.setPassword(redissonProperties.getPassword());
        }
        config.setCodec(new JsonJacksonCodec());
        return Redisson.create(config);
    }

    /**
     * 集群模式的 redisson 客户端
     *
     * @return
     */
    @Bean
    @ConditionalOnProperty(name = "spring.redisson.mode", havingValue = "cluster")
    RedissonClient redissonCluster() {
        System.out.println("cluster redisProperties:" + redissonProperties.getCluster());

        Config config = new Config();
        String[] nodes = redissonProperties.getCluster().getNodes().split(",");
        List<String> newNodes = new ArrayList(nodes.length);
        Arrays.stream(nodes).forEach((index) -> newNodes.add(
                index.startsWith("redis://") ? index : "redis://" + index));

        ClusterServersConfig serverConfig = config.useClusterServers()
                .addNodeAddress(newNodes.toArray(new String[0]))
                .setScanInterval(
                        redissonProperties.getCluster().getScanInterval())
                .setIdleConnectionTimeout(
                        redissonProperties.getPool().getSoTimeout())
                .setConnectTimeout(
                        redissonProperties.getPool().getConnTimeout())
                .setFailedSlaveCheckInterval(redissonProperties.getCluster().getFailedSlaveCheckInterval())
                .setFailedSlaveReconnectionInterval(redissonProperties.getCluster().getFailedSlaveReconnectionInterval())
                .setRetryAttempts(
                        redissonProperties.getCluster().getRetryAttempts())
                .setRetryInterval(
                        redissonProperties.getCluster().getRetryInterval())
                .setMasterConnectionPoolSize(redissonProperties.getCluster()
                        .getMasterConnectionPoolSize())
                .setSlaveConnectionPoolSize(redissonProperties.getCluster()
                        .getSlaveConnectionPoolSize())
                .setTimeout(redissonProperties.getTimeout());
        if (StringUtils.isNotBlank(redissonProperties.getPassword())) {
            serverConfig.setPassword(redissonProperties.getPassword());
        }
        config.setCodec(new JsonJacksonCodec());
        return Redisson.create(config);
    }

    /**
     * 哨兵模式 redisson 客户端
     *
     * @return
     */

    @Bean
    @ConditionalOnProperty(name = "spring.redisson.mode", havingValue = "sentinel")
    RedissonClient redissonSentinel() {
        System.out.println("sentinel redisProperties:" + redissonProperties.getSentinel());
        Config config = new Config();
        String[] nodes = redissonProperties.getSentinel().getNodes().split(",");
        List<String> newNodes = new ArrayList(nodes.length);
        Arrays.stream(nodes).forEach((index) -> newNodes.add(
                index.startsWith("redis://") ? index : "redis://" + index));

        SentinelServersConfig serverConfig = config.useSentinelServers()
                .addSentinelAddress(newNodes.toArray(new String[0]))
                .setMasterName(redissonProperties.getSentinel().getMaster())
                .setReadMode(ReadMode.SLAVE)
                .setFailedSlaveCheckInterval(redissonProperties.getSentinel().getFailedSlaveCheckInterval())
                .setFailedSlaveReconnectionInterval(redissonProperties.getSentinel().getFailedSlaveReconnectionInterval())
                .setTimeout(redissonProperties.getTimeout())
                .setMasterConnectionPoolSize(redissonProperties.getSentinel().getMasterConnectionPoolSize())
                .setSlaveConnectionPoolSize(redissonProperties.getSentinel().getSlaveConnectionPoolSize());

        if (StringUtils.isNotBlank(redissonProperties.getPassword())) {
            serverConfig.setPassword(redissonProperties.getPassword());
        }
        config.setCodec(new JsonJacksonCodec());
        return Redisson.create(config);
    }
}