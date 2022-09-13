package com.yuxuan66.admin.modules.web.system.service;

import com.yuxuan66.admin.cache.annotation.RealKey;
import com.yuxuan66.admin.cache.redis.RedisKit;
import com.yuxuan66.admin.support.base.resp.Rs;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.ibatis.cache.CacheKey;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

/**
 * @author Sir丶雨轩
 * @since 2022/9/13
 */
@Service
@RequiredArgsConstructor
public class CacheService {

    private final RedisKit redisKit;

    /**
     * 清除系统内所有缓存
     * @return 标准返回
     */
    @SneakyThrows
    public Rs clearAll(){
        for (Field field : CacheKey.class.getDeclaredFields()) {
            if(field.isAnnotationPresent(RealKey.class)){
                redisKit.del((String) field.get(CacheKey.class));
            }
        }
        return Rs.ok();
    }

}
