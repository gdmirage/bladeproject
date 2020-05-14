# redis-spring-boot-starter
封装的 redis 插件， 使用redisson进行redis操作。
# 配置文件项

``` properties 
# 默认的数据库
spring.redisson.database=0
# 密码(如果有设置的话)
spring.redisson.password=
# 超时时间
spring.redisson.timeout=3000
# 必填， sentinel(哨兵模式)/cluster(集群模式)/single(单机模式)
spring.redisson.mode=cluster
# 连接池配置
spring.redisson.pool.max-idle=16
spring.redisson.pool.min-idle=8
spring.redisson.pool.max-active=8
spring.redisson.pool.max-wait=3000
spring.redisson.pool.conn-timeout=3000
spring.redisson.pool.so-timeout=3000
spring.redisson.pool.size=10
# 单机配置
spring.redisson.single.host=192.168.166.202
spring.redisson.single.port=6379
# 集群配置
spring.redisson.cluster.scan-interval=1000
spring.redisson.cluster.nodes=192.168.166.203:7000,192.168.166.203:7001,192.168.166.203:7002,192.168.166.203:7003,192.168.166.203:7004,192.168.166.203:7005
spring.redisson.cluster.read-mode=SLAVE
spring.redisson.cluster.retry-attempts=3
spring.redisson.cluster.retry-interval=1500
# 哨兵配置
spring.redisson.sentinel.master=mymaster
spring.redisson.sentinel.nodes=192.168.166.197:6380
spring.redisson.sentinel.master-onlyWrite=true
spring.redisson.sentinel.fail-max=3
```