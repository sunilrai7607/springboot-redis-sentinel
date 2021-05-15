package com.sbr.rest.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@Slf4j
public class CacheConfig {

    @Autowired
    private CacheSettings cacheSettings;

    @Autowired
    private AppCacheSettings appCacheSettings;

    @Autowired
    private RedisProperties redisProperties;

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {

        RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration()
                .master(redisProperties.getSentinel().getMaster());

        redisProperties.getSentinel().getNodes().forEach(s -> sentinelConfig.sentinel(s, Integer.valueOf(redisProperties.getPort())));
        sentinelConfig.setPassword(RedisPassword.of(redisProperties.getPassword()));

        return new LettuceConnectionFactory(sentinelConfig);
    }

    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(600))
                .disableCachingNullValues();
    }

    private RedisCacheConfiguration buildRedisCacheConfig(CacheSettingsModel cachesProperties) {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(Long.parseLong(cachesProperties.getTimeToLiveSeconds())))
                .disableCachingNullValues();
    }

    @Bean
    public RedisCacheManager cacheManager() {

        Map<String, RedisCacheConfiguration> cacheConfigs = new HashMap<>();

        Map<String, CacheSettingsModel> cacheConfigMap = cacheSettings.getDefaultConfigs()
                .stream()
                .collect(Collectors.toMap(CacheSettingsModel::getCacheName, CacheSettingsModel::new));

        log.info("cacheConfigMap : {}", cacheConfigMap);

        Map<String, String> appCacheMap = appCacheSettings.getAppCacheMap();

        appCacheMap.forEach((key, value) -> cacheConfigs.put(key, buildRedisCacheConfig(cacheConfigMap.get(value))));

        return RedisCacheManager.builder(redisConnectionFactory())
                .cacheDefaults(cacheConfiguration())
                .withInitialCacheConfigurations(cacheConfigs)
                .transactionAware()
                .build();
    }

}
