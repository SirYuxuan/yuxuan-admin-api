package com.yuxuan66.admin.cache;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.yuxuan66.admin.common.consts.MQQueue;
import com.yuxuan66.admin.support.exception.BizException;

/**
 * MQ相关工具类
 *
 * @author Sir丶雨轩
 * @since 2022/9/20
 */
public final class MQUtil {

    /**
     * 发送一条点对点（Direct）的消息
     *
     * @param queue 队列
     * @param msg   消息内容
     */
    public static void send(MQQueue queue, Object msg) {
        if (msg == null) {
            throw new BizException("error.mqPush");
        }
        StaticComponent.rabbitTemplate.convertAndSend(queue.getName(), JSON.toJSONString(msg, JSON.DEFAULT_GENERATE_FEATURE & ~SerializerFeature.WriteEnumUsingName.mask));
    }
}
