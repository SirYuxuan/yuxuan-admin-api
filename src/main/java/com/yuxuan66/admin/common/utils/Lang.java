package com.yuxuan66.admin.common.utils;

import java.sql.Timestamp;

/**
 * @author Sir丶雨轩
 * @since 2022/9/16
 */
public class Lang {

    public static Timestamp getTime(long time){
        return new Timestamp(time);
    }

    public static Timestamp getTime(){
        return getTime(System.currentTimeMillis());
    }
}
