package com.yuxuan66.admin.support.aspect.log;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import com.alibaba.fastjson.JSON;
import com.yuxuan66.admin.cache.MQUtil;
import com.yuxuan66.admin.common.consts.MQQueue;
import com.yuxuan66.admin.support.aspect.log.annotation.Log;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 日志切面
 *
 * @author Sir丶雨轩
 * @since 2022/9/20
 */
@Aspect
@Component
@RequiredArgsConstructor
public class LogAspect {

    @Pointcut("@annotation(com.yuxuan66.admin.support.aspect.log.annotation.Log)")
    public void logCut() {
    }

    @Around(value = "logCut()")
    public Object saveSysLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // 计时器
        TimeInterval timeInterval = DateUtil.timer();

        // 方法参数
        Object[] args = joinPoint.getArgs();
        String params = JSON.toJSONString(args);
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();

        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            throw e;
        }

        //获取操作
        Log log = method.getAnnotation(Log.class);

        // 发送消息，保存日志
        com.yuxuan66.admin.modules.web.log.entity.Log saveLog = new com.yuxuan66.admin.modules.web.log.entity.Log();
        saveLog.setTime(timeInterval.interval());
        saveLog.setMethod(method.getName());
        saveLog.setClazz(joinPoint.getTarget().getClass().getName());
        saveLog.setParam(params);
        saveLog.setBody(JSON.toJSONString(result));
        saveLog.setTitle(log.title());
        saveLog.setEnTitle(log.enTitle());
        saveLog.setInfo();

        MQUtil.send(MQQueue.LOG, saveLog);

        return result;
    }
}
