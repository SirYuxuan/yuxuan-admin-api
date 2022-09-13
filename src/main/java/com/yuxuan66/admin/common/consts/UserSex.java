package com.yuxuan66.admin.common.consts;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

/**
 * 用户性别枚举
 * @author Sir丶雨轩
 * @since 2022/9/13
 */
public enum UserSex {

    /**
     * 男
     */
    MALE(0),
    /**
     * 女
     */
    FEMALE(1),
    /**
     * 未知
     */
    UNKNOWN(2);
    @EnumValue
    @JsonValue
    private final Integer value;

    UserSex(Integer value) {
        this.value = value;
    }

    public Integer value(){
        return this.value;
    }

    public boolean equals(Integer status){
        return Objects.equals(status,this.value);
    }
}
