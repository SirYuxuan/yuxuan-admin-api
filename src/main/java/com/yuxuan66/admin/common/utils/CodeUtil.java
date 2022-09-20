package com.yuxuan66.admin.common.utils;

import cn.hutool.core.util.RandomUtil;
import com.yuxuan66.admin.cache.CacheKey;
import com.yuxuan66.admin.cache.ConfigKit;

/**
 * 获取验证码
 *
 * @author Sir丶雨轩
 * @since 2022/9/20
 */
public final class CodeUtil {

    /**
     * 获取系统配置的指定位数的验证码
     * @return 验证码
     */
    public static String get() {
        return RandomUtil.randomString(ConfigKit.get(CacheKey.CONFIG_CODE_LENGTH, Integer.class)).toUpperCase();
    }

    /**
     * 获取系统配置的指定位数的验证码,数字类型
     * @return 验证码
     */
    public static String getNumber() {
        return RandomUtil.randomNumbers(ConfigKit.get(CacheKey.CONFIG_CODE_LENGTH, Integer.class)).toUpperCase();
    }
}
