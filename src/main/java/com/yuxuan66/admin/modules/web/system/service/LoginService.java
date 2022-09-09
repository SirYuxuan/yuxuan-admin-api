package com.yuxuan66.admin.modules.web.system.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuxuan66.admin.modules.cache.ConfigKit;
import com.yuxuan66.admin.modules.web.entity.system.dto.LoginDto;
import com.yuxuan66.admin.modules.web.system.entity.User;
import com.yuxuan66.admin.modules.web.system.mapper.UserMapper;
import com.yuxuan66.admin.support.base.BaseService;
import com.yuxuan66.admin.support.base.resp.Rs;
import org.springframework.stereotype.Service;

/**
 * 用户登录/退出
 *
 * @author Sir丶雨轩
 * @since 2022/9/8
 */
@Service
public class LoginService extends BaseService<User, UserMapper> {


    /**
     * 用户登录
     *
     * @param loginDto 登录信息
     * @return token等用户基本信息
     */
    public Rs login(LoginDto loginDto) {

        User user = getOne(new QueryWrapper<User>().eq("username", loginDto.getUsername()));
      /*  if (user == null) {
            return Rs.error("没有找到登录账户");
        }*/
        ConfigKit.get("",true);


        return Rs.ok();
    }

}
