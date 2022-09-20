package com.yuxuan66.admin.common.consts;

/**
 * @author Sir丶雨轩
 * @since 2022/9/20
 */
public enum MQQueue {

    LOG("topic.log"),
    LOG_CODE("topic.code.log")

    ;
    private final String name;

    MQQueue(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
