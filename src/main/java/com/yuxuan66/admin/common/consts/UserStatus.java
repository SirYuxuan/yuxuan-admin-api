package com.yuxuan66.admin.common.consts;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

/**
 * 用户状态枚举
 * @author Sir丶雨轩
 * @since 2022/9/13
 */
public enum UserStatus {

    /**
     * 正常
     */
    NORMAL(0),
    /**
     * 冻结
     */
    FROZEN(1),
    /**
     * 锁定
     */
    LOCKING(2);
    @EnumValue
    @JsonValue
    private final Integer value;

    UserStatus(Integer value) {
        this.value = value;
    }

    public Integer value(){
        return this.value;
    }

    public boolean equals(Integer status){
        return Objects.equals(status,this.value);
    }


}
