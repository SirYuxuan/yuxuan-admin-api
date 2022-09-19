package com.yuxuan66.admin.common.consts;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

/**
 * 用户状态枚举
 *
 * @author Sir丶雨轩
 * @since 2022/9/13
 */
public enum UserStatus {

    /**
     * 正常
     */
    NORMAL(0, "正常", "NORMAL"),
    /**
     * 冻结
     */
    FROZEN(1, "冻结", "FROZEN"),
    /**
     * 锁定
     */
    LOCKING(2, "锁定", "LOCKING");
    @JsonValue
    @EnumValue
    private final Integer value;
    private final String name;
    private final String enName;

    UserStatus(Integer value, String name, String enName) {
        this.value = value;
        this.name = name;
        this.enName = enName;
    }

    public Integer value() {
        return this.value;
    }

    public String getName() {
        return this.name;
    }

    public String getEnName() {
        return this.enName;
    }

    public boolean equals(Integer status) {
        return Objects.equals(status, this.value);
    }


}
