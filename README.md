### Redis Sentinel Configuration and Encryption policy
## Configuration

```properties
spring.redis.sentinel.master=mymaster #name of redis server
spring.redis.sentinel.nodes=127.0.0.1:26379,127.0.0.1:26380 #deliminated list of sentinels.
```
```yaml
cache:
    config:
        defaultConfigs:
        -  cacheName: ONE_HOUR_CACHE         
           timeToLiveSeconds: 3600 
        -  cacheName: TEN_MINUTES_CACHE
           timeToLiveSeconds: 600

  appCaches:
    appCacheMap:
        serviceOne_cache: ONE_HOUR_CACHE
        serviceTwo_cache: TEN_MINUTES_CACHE   

spring:
  cache:
    type: redis
  redis:
    port: 6666
    password: 123pwd
    sentinel:
      master: masterredis
      nodes:
        - 10.0.0.16
        - 10.0.0.17
        - 10.0.0.18
    lettuce:
      shutdown-timeout: 200ms             
```