package com.yuxuan66.admin.modules.web.entity.system.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 登录信息
 * @author Sir丶雨轩
 * @since 2022/9/8
 */
@Data
public class LoginDto {
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;
    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;
}
