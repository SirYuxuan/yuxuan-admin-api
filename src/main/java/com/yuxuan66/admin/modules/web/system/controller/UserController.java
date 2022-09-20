package com.yuxuan66.admin.modules.web.system.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.yuxuan66.admin.common.utils.Stp;
import com.yuxuan66.admin.modules.web.system.entity.User;
import com.yuxuan66.admin.modules.web.system.entity.dto.PhoneCodeDto;
import com.yuxuan66.admin.modules.web.system.entity.dto.UpdateEmailDto;
import com.yuxuan66.admin.modules.web.system.entity.dto.UpdatePassDto;
import com.yuxuan66.admin.modules.web.system.entity.query.UserQuery;
import com.yuxuan66.admin.modules.web.system.service.UserService;
import com.yuxuan66.admin.support.aspect.log.annotation.Log;
import com.yuxuan66.admin.support.base.BaseController;
import com.yuxuan66.admin.support.base.resp.Ps;
import com.yuxuan66.admin.support.base.resp.Rs;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
     * 修改密码
     *
     * @param updatePass 修改密码
     * @return 标准返回
     */
    @Log(title = "修改密码", enTitle = "Edit Password")
    @PutMapping(path = "/updatePass")
    public Rs updatePass(@RequestBody UpdatePassDto updatePass) {
        baseService.updatePass(updatePass);
        return Rs.ok();
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
    @Log(title = "删除用户", enTitle = "Del User")
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
    @Log(title = "编辑用户", enTitle = "Edit User")
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
     * 发送修改邮件的邮件
     *
     * @param mail 新邮件
     * @return 标准返回
     */
    @Log(title = "发送邮箱验证码",enTitle = "Send Email Code")
    @PutMapping(path = "/sendUpdateMail")
    public Rs sendUpdateMail(String mail) {
        return baseService.sendUpdateMail(mail);
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

    /**
     * 修改用户邮箱
     *
     * @param updateEmail 参数
     * @return 返回
     */
    @Log(title = "修改密码", enTitle = "Edit Password")
    @PutMapping(path = "/updateEmail")
    public Rs updateEmail(@Valid @RequestBody UpdateEmailDto updateEmail) {
        return baseService.updateEmail(updateEmail);
    }

    /**
     * 发送短信验证码
     * @param phoneCode 参数
     * @return 标准返回
     */
    @Log(title = "发送短信验证码",enTitle = "Send SMS Code")
    @PutMapping(path = "/sendPhoneCode")
    public Rs sendPhoneCode(@RequestBody PhoneCodeDto phoneCode) {
        return baseService.sendPhoneCode(phoneCode);
    }

    /**
     * 校验验证码是否正确
     * @param phoneCode 验证码
     * @return 标准返回
     */
    @GetMapping(path = "/checkPhoneCode")
    public Rs checkPhoneCode(PhoneCodeDto phoneCode) {
        return baseService.checkPhoneCode(phoneCode);
    }
}
