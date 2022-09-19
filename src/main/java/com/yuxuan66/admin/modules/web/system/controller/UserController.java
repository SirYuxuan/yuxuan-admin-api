package com.yuxuan66.admin.modules.web.system.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.yuxuan66.admin.common.utils.Stp;
import com.yuxuan66.admin.modules.web.system.entity.User;
import com.yuxuan66.admin.modules.web.system.entity.query.UserQuery;
import com.yuxuan66.admin.modules.web.system.service.UserService;
import com.yuxuan66.admin.support.base.BaseController;
import com.yuxuan66.admin.support.base.resp.Ps;
import com.yuxuan66.admin.support.base.resp.Rs;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Set;

/**
 * @author Sir丶雨轩
 * @since 2022/9/16
 */
@SaCheckLogin
@RestController
@RequestMapping(path = "/user")
@RequiredArgsConstructor
public class UserController extends BaseController<UserService> {

    /**
     * 获取当前登录用户的信息
     *
     * @return 用户信息
     */
    @GetMapping(path = "/info")
    public Rs info() {
        return Rs.ok(baseService.getUserById(Stp.getId()));
    }

    /**
     * 分页查询用户列表
     *
     * @param userQuery 用户查询
     * @return 用户列表
     */
    @GetMapping
    public Ps list(UserQuery userQuery) {
        return baseService.list(userQuery);
    }

    /**
     * 批量删除用户数据，会删除用户的附属信息
     *
     * @param ids 用户id列表
     * @return 标准返回
     */
    @DeleteMapping
    public Rs del(@RequestBody Set<Long> ids) {
        return baseService.del(ids);
    }

    /**
     * 新增用户
     *
     * @param resources 用户
     * @return 标准返回
     */
    @PostMapping
    public Rs add(@RequestBody User resources) {
        return baseService.add(resources);
    }

    /**
     * 修改用户
     *
     * @param resources 用户
     * @return 标准返回
     */
    @PutMapping
    public Rs edit(@RequestBody User resources) {
        baseService.edit(resources);
        return Rs.ok();
    }

    /**
     * 判断指定字段指定值的用户是否存在
     *
     * @param field 字段
     * @param data  值
     * @return 是否存在
     */
    @GetMapping(path = "/checkExist")
    public Rs checkExist(String field, String data, String type, Long id) {
        return Rs.ok(baseService.checkExist(field, data, type, id) > 0);
    }

    /**
     * 导出用户列表
     *
     * @param userQuery 查询条件
     * @throws IOException IOException
     */
    @GetMapping(path = "/download")
    public void download(UserQuery userQuery) throws IOException {
        baseService.download(userQuery);
    }
}
