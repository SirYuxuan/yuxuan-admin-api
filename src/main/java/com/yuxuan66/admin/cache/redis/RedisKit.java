package com.yuxuan66.admin.cache.redis;

import cn.hutool.core.util.StrUtil;
import com.yuxuan66.admin.support.base.General;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Objects;

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

    /**
     * 判断一组key是否存在
     *
     * @param keys key列表
     * @return 是否存在
     */
    public boolean exist(String... keys) {
        for (String key : keys) {
            if (Boolean.FALSE.equals(redisTemplate.hasKey(key))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断一个缓存的Hash对象的key否存在
     *
     * @param key  缓存key
     * @param hKey hashKey
     * @return 是否存在
     */
    public boolean hExist(String key, String hKey) {
        if (!exist(key)) {
            return false;
        }
        return redisTemplate.opsForHash().hasKey(key, hKey);
    }

    /**
     * 删除一组缓存
     * @param keys key列表
     */
    public void del(String ...keys){
        redisTemplate.delete(Arrays.stream(keys).toList());
    }

    /**
     * 从缓存中获取字符串数据
     *
     * @param key key
     * @return 字符串数据
     */
    public String get(String key) {
        if (exist(key)) {
            return Objects.requireNonNull(redisTemplate.opsForValue().get(key)).toString();
        }
        return StrUtil.EMPTY;
    }

    /**
     * 从缓存的hash对象中获取指定key的数据
     *
     * @param key  缓存key
     * @param hKey hashKey
     * @return 数据
     */
    public String hGet(String key, String hKey) {
        if (hExist(key, hKey)) {
            return Objects.requireNonNull(redisTemplate.opsForHash().get(key, hKey)).toString();
        }
        return StrUtil.EMPTY;
    }

    /**
     * 设置字符串数据到缓存
     *
     * @param key  key
     * @param data 数据
     */
    public void set(String key, String data) {
        redisTemplate.opsForValue().set(key, data);
    }

    /**
     * 设置字符串数据到hash缓存
     *
     * @param key  key
     * @param hKey hashKey
     * @param data 数据
     */
    public void hSet(String key, String hKey, String data) {
        redisTemplate.opsForHash().put(key, hKey, data);
    }


}
