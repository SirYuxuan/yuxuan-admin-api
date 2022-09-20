package com.yuxuan66.admin.cache;

import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.extra.spring.SpringUtil;
import com.yuxuan66.admin.cache.redis.RedisKit;
import com.yuxuan66.admin.modules.web.system.mapper.ConfigMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 把一些常用组件设置为全局静态对象
 *
 * @author Sir丶雨轩
 * @since 2022/9/8
 */
@Slf4j
@Component
public final class StaticComponent {

    /**
     * redis操作工具库
     */
    public static RedisKit redisKit;
    /**
     * 系统配置操作mapper
     */
    public static ConfigMapper configMapper;
    /**
     * rsa操作对象
     */
    public static RSA rsaKit;
    /**
     * i18n操作对象
     */
    public static MessageSource messageSource;
    /**
     * MQ操作对象
     */
    public static RabbitTemplate rabbitTemplate;


    /**
     * 处理系统内的全局静态组件
     */
    @PostConstruct
    public void initCache() {
        log.info("开始初始化全局静态组件...");
        redisKit = SpringUtil.getBean(RedisKit.class);
        log.info("redisKit[{}] init success...", redisKit.toString());
        configMapper = SpringUtil.getBean(ConfigMapper.class);
        log.info("configMapper[{}] init success...", configMapper.toString());
        rsaKit = new RSA(ConfigKit.get(CacheKey.CONFIG_RSA_PRIVATE), ConfigKit.get(CacheKey.CONFIG_RSA_PUBLIC));
        log.info("rsa[{}] init success...", rsaKit);
        messageSource = SpringUtil.getBean(MessageSource.class);
        log.info("messageSource[{}] init success...", messageSource);
        rabbitTemplate = SpringUtil.getBean(RabbitTemplate.class);
        log.info("rabbitTemplate[{}] init success...", rabbitTemplate);

    }


}
