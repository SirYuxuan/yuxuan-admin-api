package com.yuxuan66.admin.modules.web.system.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.yuxuan66.admin.modules.web.system.service.MenuService;
import com.yuxuan66.admin.support.base.BaseController;
import com.yuxuan66.admin.support.base.resp.Rs;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sir丶雨轩
 * @since 2022/9/16
 */
@SaCheckLogin
@RestController
@RequestMapping(path = "/menu")
@RequiredArgsConstructor
public class MenuController extends BaseController<MenuService> {

    /**
     * 构建左侧菜单
     * 移除掉所有按钮级菜单
     *
     * @return 菜单
     */
    @GetMapping(path = "/build")
    public Rs build() {
        return baseService.build();
    }
}
