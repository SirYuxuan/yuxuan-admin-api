package com.yuxuan66.admin.modules.web.system.controller;

import com.yuxuan66.admin.modules.web.system.entity.dto.LoginDto;
import com.yuxuan66.admin.modules.web.system.service.AuthService;
import com.yuxuan66.admin.support.base.BaseController;
import com.yuxuan66.admin.support.base.resp.Rs;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 用户登录/退出
 * @author Sir丶雨轩
 * @since 2022/9/8
 */
@RestController
@RequiredArgsConstructor
public class AuthController extends BaseController {

    private final AuthService authService;

    /**
     * 用户登录
     * @param loginDto 登录信息
     * @return 用户信息
     */
    @PostMapping(path = "/login")
    public Rs login(@Valid @RequestBody LoginDto loginDto){
        return authService.login(loginDto);
    }
}
