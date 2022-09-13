package com.yuxuan66.admin.common.utils;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.lang.Dict;
import com.yuxuan66.admin.common.consts.LoginType;
import com.yuxuan66.admin.modules.web.system.entity.User;

/**
 * 扩展SaToken提供的工具类
 *
 * @author Sir丶雨轩
 * @since 2022/9/13
 */
public final class Stp extends StpUtil {

    private static final String SESSION_USER_KEY = "stp:user";

    /**
     * 用户登录，保存用户信息
     *
     * @param user 用户
     */
    public static void login(User user) {
        login(user.getId(), LoginType.PC.toString());
        getSession().set(SESSION_USER_KEY, user);
    }

    /**
     * 获取token数据
     *
     * @return token数据
     */
    public static Dict getToken() {
        return new Dict().set(getTokenName(), getTokenValue());
    }


}
