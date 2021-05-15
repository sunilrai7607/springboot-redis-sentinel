package com.sbr.rest.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class CacheSettingsModel implements Serializable {

    private String cacheName;

    private String timeToLiveSeconds;

    public CacheSettingsModel(CacheSettingsModel cacheSettingsModel) {
        this.cacheName = cacheSettingsModel.getCacheName();
        this.timeToLiveSeconds = cacheSettingsModel.getTimeToLiveSeconds();
    }
}
