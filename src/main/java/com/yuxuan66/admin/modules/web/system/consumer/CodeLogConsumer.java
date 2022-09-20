package com.yuxuan66.admin.modules.web.system.consumer;

import com.alibaba.fastjson.JSONObject;
import com.yuxuan66.admin.common.consts.CodeLogAction;
import com.yuxuan66.admin.common.consts.CodeLogType;
import com.yuxuan66.admin.common.utils.WebUtil;
import com.yuxuan66.admin.modules.web.system.entity.CodeLog;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * CodeLog队列消费者
 * @author Sir丶雨轩
 * @since 2022/9/20
 */
@Component
@RabbitListener(queues = "topic.code.log")
public class CodeLogConsumer {

    @RabbitHandler
    public void handleMessage(String message) {
        CodeLog log = JSONObject.parseObject(message,CodeLog.class);
        log.setType(CodeLogType.valueOf(log.getLogType()));
        log.setAction(CodeLogAction.valueOf(log.getActionType()));
        log.setCity(WebUtil.getCityInfo(log.getIp()));
        log.insert();
    }
}
