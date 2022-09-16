package com.yuxuan66.admin.modules.web.system.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuxuan66.admin.modules.web.system.entity.Menu;
import com.yuxuan66.admin.modules.web.system.entity.Role;
import com.yuxuan66.admin.modules.web.system.entity.User;
import com.yuxuan66.admin.modules.web.system.entity.UsersRoles;
import com.yuxuan66.admin.modules.web.system.entity.query.UserQuery;
import com.yuxuan66.admin.modules.web.system.mapper.UserMapper;
import com.yuxuan66.admin.modules.web.system.mapper.UsersRolesMapper;
import com.yuxuan66.admin.support.base.BaseService;
import com.yuxuan66.admin.support.base.resp.Ps;
import com.yuxuan66.admin.support.base.resp.Rs;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户Service
 *
 * @author Sir丶雨轩
 * @since 2022/9/16
 */
@Service
public class UserService extends BaseService<User, UserMapper> {

    @Resource
    private UsersRolesMapper usersRolesMapper;

    /**
     * 分页查询用户列表
     *
     * @param userQuery 用户查询
     * @return 用户列表
     */
    public Ps list(UserQuery userQuery) {
        return Ps.ok(baseMapper.countUser(userQuery), baseMapper.listUser(userQuery));
    }

    /**
     * 判断指定字段指定值的用户是否存在
     *
     * @param field 字段
     * @param data  值
     * @return 是否存在
     */
    public Rs checkExist(String field, String data, String type,Long id) {
        return Rs.ok(count(new QueryWrapper<User>().eq(field, data).ne("edit".equals(type), "id", id)) > 0);
    }

    /**
     * 修改用户
     *
     * @param resources 用户
     * @return 标准返回
     */
    @CacheEvict(value = "Cache:User",key = "#resources.id")
    public Rs edit(User resources) {

        resources.updateById();

        if (resources.getRoles() != null) {
            usersRolesMapper.delete(new QueryWrapper<UsersRoles>().eq("user_id", resources.getId()));
            for (Role role : resources.getRoles()) {
                UsersRoles usersRoles = new UsersRoles();
                usersRoles.setUserId(resources.getId());
                usersRoles.setRoleId(role.getId());
                usersRolesMapper.insert(usersRoles);
            }
        }
        return Rs.ok();
    }

    /**
     * 根据用户ID获取用户数据
     *
     * @param userId 用户id
     * @return 用户数据
     */
    @Cacheable(value = "Cache:User", key = "#userId")
    public User getUserById(Long userId) {
        User user = baseMapper.findUserById(userId);
        Set<String> permissions = new HashSet<>();
        // 添加一个默认的权限
        permissions.add("DEFAULT");
        permissions.addAll(user.getMenus().stream().map(Menu::getPermission).filter(StrUtil::isNotBlank).toList());
        user.setPermissions(permissions);
        return user;
    }
}
