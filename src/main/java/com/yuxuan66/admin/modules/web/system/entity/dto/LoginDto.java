package com.yuxuan66.admin.modules.web.system.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 登录信息
 *
 * @author Sir丶雨轩
 * @since 2022/9/8
 */
@Data
public class LoginDto {
    /**
     * 用户名
     */
    @NotBlank(message = "{error.form.username}")
    private String username;
    /**
     * 密码
     */
    @NotBlank(message = "{error.form.password}")
    private String password;
}
