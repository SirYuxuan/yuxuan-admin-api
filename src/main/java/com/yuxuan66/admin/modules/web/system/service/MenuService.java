package com.yuxuan66.admin.modules.web.system.service;

import com.yuxuan66.admin.common.utils.Stp;
import com.yuxuan66.admin.common.utils.tree.TreeUtil;
import com.yuxuan66.admin.modules.web.system.entity.Menu;
import com.yuxuan66.admin.modules.web.system.mapper.MenuMapper;
import com.yuxuan66.admin.modules.web.system.mapper.UserMapper;
import com.yuxuan66.admin.support.base.BaseService;
import com.yuxuan66.admin.support.base.resp.Rs;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Sir丶雨轩
 * @since 2022/9/16
 */
@Service
@RequiredArgsConstructor
public class MenuService extends BaseService<Menu, MenuMapper> {

    @Resource
    private UserMapper userMapper;

    private final UserService userService;

    /**
     * 构建左侧菜单
     * 移除掉所有按钮级菜单
     *
     * @return 菜单
     */
    public Rs build() {
        return Rs.ok(new TreeUtil<Menu>().menuList(userService.getUserById(Stp.getId()).getMenus().stream().filter(item -> item.getType() != 2).toList()));
    }


}
