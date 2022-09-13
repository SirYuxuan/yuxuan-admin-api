package com.yuxuan66.admin.modules.web.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yuxuan66.admin.support.base.BaseEntity;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 系统-用户表(SysUser)实体类
 *
 * @author Sir丶雨轩
 * @since 2022-09-08 17:14:59
 */
@Data
@TableName("sys_user")
public class User extends BaseEntity<User> implements Serializable,Cloneable {



    @Serial
    private static final long serialVersionUID = -97197633279961034L;
    
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 头像ID
     */
    private String avatar;

    /**
     * 用户状态，0=正常，1=冻结，2=锁定
     */
    private Integer status;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 性别 0=女,1=男,2=未知
     */
    private Integer sex;
    /**
     * 登录时间
     */
    private Timestamp loginTime;
    /**
     * 登录IP
     */
    private String loginIp;
    /**
     * 登录城市
     */
    private String loginCity;


    @Override
    public User clone() {
        try {
            return (User) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}

