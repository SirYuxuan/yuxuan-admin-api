package com.yuxuan66.admin.cache.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标识一个key为真实的key，有些key可能是二级hashKey
 *
 * @author Sir丶雨轩
 * @since 2022/9/13
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RealKey {
}
