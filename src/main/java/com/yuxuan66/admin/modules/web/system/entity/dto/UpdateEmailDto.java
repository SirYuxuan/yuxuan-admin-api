package com.yuxuan66.admin.modules.web.system.entity.dto;

import lombok.Data;

/**
 * @author Sir丶雨轩
 * @since 2022/9/20
 */
@Data
public class UpdateEmailDto {
    private String email;
    private String code;
    private String pass;
    private String uuid;
}
