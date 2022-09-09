package com.yuxuan66.admin.modules.cache;

import com.yuxuan66.admin.modules.cache.redis.RedisKit;
import lombok.extern.slf4j.Slf4j;

/**
 * Config的配套查询工具，使用缓存
 *
 * @author Sir丶雨轩
 * @since 2022/9/8
 */
@Slf4j
public final class ConfigKit {

    private static RedisKit redisKit;


    public static String get(String key, boolean force) {
        System.out.println(redisKit);
        return "";
    }

    public static void initialize() {
        redisKit = StaticComponent.redisKit;
        log.info("初始化");
    }

}
