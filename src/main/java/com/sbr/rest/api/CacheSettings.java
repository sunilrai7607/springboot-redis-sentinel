package com.sbr.rest.api;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "cache.config")
public class CacheSettings implements Serializable {

    private List<CacheSettingsModel> defaultConfigs;
}
