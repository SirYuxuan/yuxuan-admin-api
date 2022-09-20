package com.yuxuan66.admin.modules.web.system.controller;

import com.yuxuan66.admin.modules.web.system.service.CaptchaService;
import com.yuxuan66.admin.support.base.BaseController;
import com.yuxuan66.admin.support.base.resp.Rs;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sir丶雨轩
 * @since 2022/9/20
 */
@RestController
public class CaptchaController extends BaseController<CaptchaService> {
    /**
     * 生成验证码
     *
     * @return 结果
     */
    @GetMapping(path = "/captchaImage")
    public Rs captchaImage(){
        return baseService.captchaImage();
    }
}
