package com.idus.work.common.constant;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;
import java.util.stream.Stream;

public enum Gender {
    NONE, MALE, FEMALE;

    @JsonCreator
    public static Gender create(String requestValue) {
        return Stream.of(values())
                .filter(gender -> gender.toString().equalsIgnoreCase(requestValue))
                .findFirst()
                .orElse(NONE);
    }
}
