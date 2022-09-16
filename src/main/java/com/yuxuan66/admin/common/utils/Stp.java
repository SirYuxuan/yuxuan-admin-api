package com.yuxuan66.admin.common.utils;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.system.SystemUtil;
import com.yuxuan66.admin.common.consts.LoginType;
import com.yuxuan66.admin.modules.web.system.entity.User;

/**
 * 扩展SaToken提供的工具类
 *
 * @author Sir丶雨轩
 * @since 2022/9/13
 */
public class Stp {

    private static final String SESSION_USER_KEY = "stp:user";

    /**
     * 用户登录，保存用户信息
     *
     * @param user 用户
     */
    public static void login(User user) {
        StpUtil.login(user.getId(), LoginType.PC.toString());
        StpUtil.getSession().set(SESSION_USER_KEY, user);
    }

    /**
     * 获取当前登录用户
     *
     * @return 登录用户
     */
    public static User get() {
        try {
            return StpUtil.getSession().get(SESSION_USER_KEY, User::new);
        } catch (Exception e) {
            if (SystemUtil.getOsInfo().isWindows()) {
                User user = new User();
                user.setId(1L);
                user.setNickName("Inner DEBUG");
                return user;
            }
        }
        return null;
    }


    /**
     * 获取当前登录用户id
     *
     * @return 用户id
     */
    public static Long getId() {
        return get().getId();
    }

    /**
     * 获取token数据
     *
     * @return token数据
     */
    public static Dict getToken() {
        return new Dict().set(StpUtil.getTokenName(), StpUtil.getTokenValue());
    }


    /**
     * 会话注销
     */
    public static void logout() {
        StpUtil.logout();
    }

}
