package com.yuxuan66.admin.support.other.phone;

import lombok.SneakyThrows;

/**
 * 短信发送工具类
 * @author Sir丶雨轩
 * @since 2022/9/20
 */
public interface PhoneUtil {


    /**
     * 发送验证码
     *
     * @param phone    手机号
     * @param template 模板ID
     * @param param    替换参数
     */
    void send(String phone, String template, String param);
}
