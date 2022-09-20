package com.yuxuan66.admin.modules.web.log.consumer.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.yuxuan66.admin.common.utils.WebUtil;
import com.yuxuan66.admin.modules.web.log.entity.Log;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 收到日志消息
 *
 * @author Sir丶雨轩
 * @since 2022/9/20
 */
@Component
@RabbitListener(queues = "topic.log")
public class LogConsumer {


    @RabbitHandler
    public void handleMessage(String message) {
        Log log = JSON.parseObject(message,Log.class);
        log.setCity(WebUtil.getCityInfo(log.getIp()));
        log.insert();
    }
}
