package com.yuxuan66.admin.modules.web.system.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Sir丶雨轩
 * @since 2022/9/13
 */
@Data
public class RegisterDto {

    @NotBlank(message = "{error.form.username}")
    private String username;
    @NotBlank(message = "{error.form.code}")
    private String code;
    @NotBlank(message = "{error.form.uuid}")
    private String uuid;
    @NotBlank(message = "{error.form.password}")
    private String password;
}
