package com.yuxuan66.admin.cache;

import com.yuxuan66.admin.cache.annotation.RealKey;

/**
 * 缓存Key
 *
 * @author Sir丶雨轩
 * @since 2022/9/13
 */
public interface CacheKey {

    /**
     * 用户默认头像
     */

    String DEFAULT_AVATAR = "DEFAULT_DATA:AVATAR";
    /**
     * 用户默认角色
     */
    String DEFAULT_ROLE = "DEFAULT_DATA:ROLE";

    /**
     * 验证码图片
     */
    String CAPTCHA_CODE = "CAPTCHA_CODE";

    // ************************ SysConfig内容 ***************************/

    @RealKey
    String CONFIG = "SYS_CONFIG";

    /**
     * rsa 私钥
     */
    String CONFIG_RSA_PRIVATE = "rsaPrivate";
    /**
     * rsa 公钥
     */
    String CONFIG_RSA_PUBLIC = "rsaPublic";
}
