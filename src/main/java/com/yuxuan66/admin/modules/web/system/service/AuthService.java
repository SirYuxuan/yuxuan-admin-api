package com.yuxuan66.admin.modules.web.system.service;


import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuxuan66.admin.cache.CacheKey;
import com.yuxuan66.admin.cache.StaticComponent;
import com.yuxuan66.admin.cache.redis.RedisKit;
import com.yuxuan66.admin.common.consts.UserStatus;
import com.yuxuan66.admin.common.utils.PasswordUtil;
import com.yuxuan66.admin.common.utils.Stp;
import com.yuxuan66.admin.modules.web.entity.system.dto.LoginDto;
import com.yuxuan66.admin.modules.web.system.entity.User;
import com.yuxuan66.admin.modules.web.system.entity.dto.RegisterDto;
import com.yuxuan66.admin.modules.web.system.mapper.UserMapper;
import com.yuxuan66.admin.support.base.BaseService;
import com.yuxuan66.admin.support.base.resp.Rs;
import com.yuxuan66.admin.support.exception.BizException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 用户登录/退出
 *
 * @author Sir丶雨轩
 * @since 2022/9/8
 */
@Service
@RequiredArgsConstructor
public class AuthService extends BaseService<User, UserMapper> {

    private final RedisKit redis;

    /**
     * 用户登录
     *
     * @param loginDto 登录信息
     * @return token等用户基本信息
     */
    public Rs login(LoginDto loginDto) {

        User user = getOne(new QueryWrapper<User>().eq("username", loginDto.getUsername()));
        if (user == null) {
            return Rs.errorI("login.notFound");
        }
        if (StrUtil.isBlank(user.getPassword())) {
            return Rs.errorI("login.notPassword");
        }
        if (UserStatus.FROZEN.equals(user.getStatus())) {
            return Rs.errorI("login.frozen");
        }
        if (UserStatus.LOCKING.equals(user.getStatus())) {
            return Rs.errorI("login.locking");
        }
        // 用私钥对前台传递密码进行解密
        String password = StaticComponent.rsaKit.decryptStr(loginDto.getPassword(), KeyType.PrivateKey);
        if (!PasswordUtil.validatePassword(password, user.getPassword())) {
            return Rs.errorI("login.errorPassword");
        }
        Stp.login(user);

        return Rs.ok(Stp.getToken());
    }

    /**
     * 用户注册
     * @param registerDto 用户信息
     * @return 标准返回
     */
    public Rs register(RegisterDto registerDto){
        checkCode(registerDto);

        User user = new User();
        user.insert();

        return Rs.ok();
    }

    /**
     * 退出登录
     */
    public void logout(){
        Stp.logout();
    }


    /**
     * 校验注册验证码是否正确
     * @param registerDto 注册信息
     */
    private void checkCode(RegisterDto registerDto){
        String code = Convert.toStr(redis.hGet(CacheKey.CAPTCHA_CODE ,registerDto.getUuid()));
        redis.del(registerDto.getUuid());
        if (StrUtil.isBlank(registerDto.getCode()) || !registerDto.getCode().equalsIgnoreCase(code)) {
           throw new BizException("error.captchaCode");
        }
    }




}
