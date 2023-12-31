package com.yuxuan66.admin.common.consts;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

/**
 * 用户性别枚举
 *
 * @author Sir丶雨轩
 * @since 2022/9/13
 */
public enum UserSex {

    /**
     * 男
     */
    MALE(0, "男", "MALE"),
    /**
     * 女
     */
    FEMALE(1, "女", "FEMALE"),
    /**
     * 未知
     */
    UNKNOWN(2, "未知", "UNKNOWN");
    @JsonValue
    @EnumValue
    private final Integer value;
    private String name;
    private String enName;

    UserSex(Integer value, String name, String enName) {
        this.value = value;
        this.name = name;
        this.enName = enName;
    }

    public String getName() {
        return this.name;
    }

    public String getEnName() {
        return this.enName;
    }

    public Integer value() {
        return this.value;
    }

    public boolean equals(Integer status) {
        return Objects.equals(status, this.value);
    }


}
