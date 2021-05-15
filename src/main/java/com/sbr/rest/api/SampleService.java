package com.sbr.rest.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SampleService {

    @Cacheable("serviceOne_cache")
    public List<String> serviceOne(String param) {
    return null;
    }
}
