package com.yuxuan66.admin.modules.web.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Sir丶雨轩
 * @since 2022/9/13
 */
@Data
@Accessors(chain = true)
@TableName("sys_users_roles")
public class UsersRoles extends Model<UsersRoles> implements Serializable {

    @Serial
    private static final long serialVersionUID = -6307949042129238647L;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 角色id
     */
    private Long roleId;
}
