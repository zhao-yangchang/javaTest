package com.zhaoyc.javatest.learn.common.enums;

import lombok.Getter;

@Getter
public enum AreaEnum {

    CHINA("CHINA"),
    AMERICA("AMERICA")
    ;

    private String desc;

    AreaEnum(String desc) {
        this.desc = desc;
    }

}
