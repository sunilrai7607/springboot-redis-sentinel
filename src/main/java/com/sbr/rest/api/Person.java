package com.sbr.rest.api;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Person implements Serializable {

    private String id;

    private String name;
}
