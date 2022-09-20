package com.yuxuan66.admin.common.consts;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author Sir丶雨轩
 * @since 2022/9/20
 */
public enum CodeLogAction {


    EDIT_EMAIL(0, "修改邮箱", "Edit Email"),
    EDIT_PHONE(1, "修改手机号", "Edit Phone");

    @JsonValue
    @EnumValue
    private final Integer value;
    private String name;
    private String enName;

    CodeLogAction(Integer value, String name, String enName) {
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

    public static CodeLogAction valueOf(Integer value) {
        return Arrays.stream(CodeLogAction.values()).filter(item -> Objects.equals(item.value, value)).findAny().orElse(null);
    }
}
