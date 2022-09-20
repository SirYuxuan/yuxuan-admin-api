package com.yuxuan66.admin.modules.web.system.service;

import cn.hutool.core.util.IdUtil;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import com.yuxuan66.admin.cache.CacheKey;
import com.yuxuan66.admin.cache.ConfigKit;
import com.yuxuan66.admin.cache.StaticComponent;
import com.yuxuan66.admin.support.base.resp.Rs;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 验证码生成处理
 *
 * @author Sir丶雨轩
 * @since 2022/9/20
 */
@Service
@RequiredArgsConstructor
public class CaptchaService {

    /**
     * 生成验证码
     *
     * @return 结果
     */
    public Rs captchaImage() {

        Captcha captcha = new SpecCaptcha(111, 36);
        // 几位数运算，默认是两位
        captcha.setLen(ConfigKit.get(CacheKey.CONFIG_CODE_LENGTH,Integer.class));
        // 获取运算的结果
        String result = captcha.text();
        String uuid = IdUtil.simpleUUID();
        // 保存
        Map<String, Object> imgResult = new HashMap<>(2) {{
            put("img", captcha.toBase64());
            put("uuid", uuid);
        }};

        StaticComponent.redisKit.set(CacheKey.CODE_CACHE + uuid, result, ConfigKit.get(CacheKey.CONFIG_CODE_EXPIRE_TIME, Long.class));

        return Rs.ok(imgResult);
    }

}
