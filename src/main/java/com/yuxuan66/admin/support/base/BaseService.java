package com.yuxuan66.admin.support.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuxuan66.admin.cache.redis.RedisKit;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 基础Service
 *
 * @author Sir丶雨轩
 * @since 2022/9/8
 */
public class BaseService<T, M extends BaseMapper<T>> extends ServiceImpl<M, T> {

    @Autowired
    protected RedisKit redisKit;
}
