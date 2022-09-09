package com.yuxuan66.admin.support.base;

/**
 * 增强Object提供一些常用方法
 * @author Sir丶雨轩
 * @since 2022/9/8
 */
public class General {


    @Override
    public String toString() {
        return "@" + Integer.toHexString(this.hashCode());
    }
}
