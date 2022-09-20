package com.yuxuan66.admin.common.consts;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author Sir丶雨轩
 * @since 2022/9/20
 */
public enum CodeLogType {

    PHONE(0, "手机", "Phone"),
    EMAIL(1, "邮箱", "Email");

    @JsonValue
    @EnumValue
    private final Integer value;
    private String name;
    private String enName;

    CodeLogType(Integer value, String name, String enName) {
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

    public static CodeLogType valueOf(Integer value){
        return Arrays.stream(CodeLogType.values()).filter(item -> Objects.equals(item.value, value)).findAny().orElse(null);
    }
}
