package com.yuxuan66.admin.support.aspect.log.annotation;

import com.yuxuan66.admin.support.aspect.log.LogType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Sir丶雨轩
 * @since 2022/9/20
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {

    LogType type() default LogType.NORMAL;

    String title();

    String enTitle();
}
