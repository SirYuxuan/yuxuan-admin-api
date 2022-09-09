package com.yuxuan66.admin.modules.cache.redis;

import com.yuxuan66.admin.support.base.General;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Redis操作工具
 *
 * @author Sir丶雨轩
 * @since 2022/9/8
 */
@Component
public class RedisKit extends General {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;



}
