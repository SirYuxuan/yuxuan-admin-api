package com.yuxuan66.admin.modules.web.system.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.yuxuan66.admin.modules.web.system.service.RoleService;
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
@RequestMapping(path = "/role")
@RequiredArgsConstructor
public class RoleController extends BaseController<RoleService> {

    /**
     * 查询系统全部角色
     * @return 全部角色
     */
    @GetMapping(path = "/queryAll")
    public Rs queryAll(){
        return Rs.ok(baseService.queryAll());
    }
}
