package com.sbr.rest.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class SampleService {

    @Cacheable(value = "serviceTwo_cache", key = "#id")
    public List<Person> serviceOne(String id) {
        log.info("Calling method : {}",id);
        return Arrays.asList(Person.builder().id(id).name("sunil").build());
    }
}
