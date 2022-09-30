package com.idus.work.common.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.Arrays;
import java.util.stream.Stream;

@Getter
public enum Gender {
    NONE("미선택"), MALE("남성"), FEMALE("여성");

    final String korGender;
    Gender(String korGender){
        this.korGender = korGender;
    }

    @JsonCreator
    public static Gender create(String requestValue) {
        return Stream.of(values())
                .filter(gender -> gender.toString().equalsIgnoreCase(requestValue))
                .findFirst()
                .orElse(NONE);
    }
}
