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


    /**
     * 系统的验证码缓存
     */
    String CODE_CACHE = "CODE_CACHE:";

    /**
     * 修改邮件校验模板
     */
    String TEMPLATE_UPDATE_EMAIL = "updateEmailTemplate";

    /**
     * 修改邮件校验模板-英文
     */
    String TEMPLATE_UPDATE_EMAIL_EN = "updateEmailTemplateEn";


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

    /**
     * 发信用户
     */
    String CONFIG_MAIL_USER = "mailUser";
    /**
     * 发信来源
     */
    String CONFIG_MAIL_FROM = "mailFrom";
    /**
     * 发信用户密码
     */
    String CONFIG_MAIL_PASS = "mailPass";
    /**
     * 发信服务地址
     */
    String CONFIG_MAIL_HOST = "mailHost";
    /**
     * 邮箱端口号
     */
    String CONFIG_MAIL_PORT = "mailPort";
    /**
     * 系统验证码长度
     */
    String CONFIG_CODE_LENGTH = "codeLen";
    /**
     * 验证码过期时间
     */
    String CONFIG_CODE_EXPIRE_TIME = "codeExpireTime";
    /**
     * 凭证-阿里云 keyId
     */
    String CONFIG_CREDENTIAL_ALI_ACCESS_KEY_ID = "aliAccessKeyId";
    /**
     * 凭证-阿里云 keySecret
     */
    String CONFIG_CREDENTIAL_ALI_ACCESS_KEY_SECRET="aliAccessKeySecret";

    // ************************ Service缓存 ***************************/
    /**
     * UserService Cache
     */
    String SERVICE_USER = "Cache:User";
    String SERVICE_ROLE = "Cache:Role";
    String SERVICE_MENU = "Cache:Menu";
    String SERVICE_USER_COUNT = "Cache:User:Count";
}
