package com.sbr.rest.api;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "cache.app-caches")
public class AppCacheSettings implements Serializable {

    private Map<String, String> appCacheMap;
}
