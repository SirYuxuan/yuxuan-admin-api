package com.yuxuan66.admin.modules.cache;

import cn.hutool.extra.spring.SpringUtil;
import com.yuxuan66.admin.modules.cache.redis.RedisKit;
import lombok.extern.slf4j.Slf4j;
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
     * 处理话系统内的全局静态组件
     */
    @PostConstruct
    public void initCache() {
        log.info("开始初始化全局静态组件...");
        redisKit = SpringUtil.getBean(RedisKit.class);
        log.info("redisKit[{}] init success...",redisKit.toString());


        ConfigKit.initialize();
    }

}
