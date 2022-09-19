package com.yuxuan66.admin.common.consts;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

/**
 * @author Sir丶雨轩
 * @since 2022/9/19
 */
public enum MenuType {

    CATALOG(0, "目录", "catalog"),
    MENU(1, "菜单", "menu"),
    BUTTON(2, "按钮", "button");;

    @JsonValue
    @EnumValue
    private final Integer value;
    private String name;
    private String enName;

    MenuType(Integer value, String name, String enName) {
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
