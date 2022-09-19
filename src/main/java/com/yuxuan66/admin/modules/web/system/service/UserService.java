package com.yuxuan66.admin.modules.web.system.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuxuan66.admin.cache.CacheKey;
import com.yuxuan66.admin.common.i18n.I18n;
import com.yuxuan66.admin.common.utils.ExcelUtil;
import com.yuxuan66.admin.common.utils.PasswordUtil;
import com.yuxuan66.admin.modules.web.system.entity.*;
import com.yuxuan66.admin.modules.web.system.entity.query.UserQuery;
import com.yuxuan66.admin.modules.web.system.mapper.UserMapper;
import com.yuxuan66.admin.modules.web.system.mapper.UsersRolesMapper;
import com.yuxuan66.admin.support.base.BaseService;
import com.yuxuan66.admin.support.base.resp.Ps;
import com.yuxuan66.admin.support.base.resp.Rs;
import com.yuxuan66.admin.support.exception.BizException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

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
    @Cacheable(value = CacheKey.SERVICE_USER_COUNT, key = "#field+':'+#data+#id")
    public long checkExist(String field, String data, String type, Long id) {
        return count(new QueryWrapper<User>().eq(field, data).ne("edit".equals(type), "id", id));
    }

    /**
     * 添加一个用户
     *
     * @param user 用户
     * @return 标准返回
     */
    @CacheEvict(value = CacheKey.SERVICE_USER_COUNT, allEntries = true)
    public Rs add(User user) {
        userLegitimacyVerification(user);
        user.setPassword(PasswordUtil.createHash(user.getUsername()));
        user.insert();
        List<UsersRoles> usersRolesList = new ArrayList<>();
        user.getRoles().forEach(item -> usersRolesList.add(new UsersRoles(user.getId(), item.getId())));
        usersRolesMapper.batchInsert(usersRolesList);
        return Rs.ok();
    }

    /**
     * 批量删除用户数据，会删除用户的附属信息
     *
     * @param ids 用户id列表
     * @return 标准返回
     */

    @Caching(evict = {
            @CacheEvict(value = CacheKey.SERVICE_USER, key = "#ids"),
            @CacheEvict(value = CacheKey.SERVICE_USER_COUNT, allEntries = true)
    })
    public Rs del(Set<Long> ids) {
        removeBatchByIds(ids);
        // 删除用户角色关联
        usersRolesMapper.delete(new QueryWrapper<RolesMenus>().in("user_id", ids));
        return Rs.ok();
    }

    /**
     * 修改用户
     *
     * @param resources 用户
     * @return 标准返回
     */
    @Caching(evict =
    @CacheEvict(value = CacheKey.SERVICE_USER_COUNT, allEntries = true),
            put = @CachePut(value = CacheKey.SERVICE_USER, key = "#resources.id"))
    public User edit(User resources) {
        // 校验用户名/邮箱/手机号是存在
        userLegitimacyVerification(resources);

        // 修改数据
        resources.updateById();

        if (resources.getRoles() != null) {
            usersRolesMapper.delete(new QueryWrapper<RolesMenus>().eq("user_id", resources.getId()));
            List<UsersRoles> usersRolesList = new ArrayList<>();
            resources.getRoles().forEach(item -> usersRolesList.add(new UsersRoles(resources.getId(), item.getId())));
            usersRolesMapper.batchInsert(usersRolesList);

        }
        return resources;
    }

    /**
     * 根据用户ID获取用户数据
     *
     * @param userId 用户id
     * @return 用户数据
     */
    @Cacheable(value = CacheKey.SERVICE_USER, key = "#userId")
    public User getUserById(Long userId) {
        User user = baseMapper.findUserById(userId);
        Set<String> permissions = new HashSet<>();
        // 添加一个默认的权限
        permissions.add("DEFAULT");
        permissions.addAll(user.getMenus().stream().map(Menu::getPermission).filter(StrUtil::isNotBlank).toList());
        user.setPermissions(permissions);
        return user;
    }

    /**
     * 导出用户列表
     *
     * @param userQuery 查询条件
     * @throws java.io.IOException IOException
     */
    public void download(UserQuery userQuery) throws IOException {

        userQuery.setSize(-1);

        List<User> userList = baseMapper.listUser(userQuery);

        List<Map<String, Object>> list = new ArrayList<>();
        for (User userDTO : userList) {
            List<String> roles = userDTO.getRoles().stream().map(Role::getName).toList();
            Map<String, Object> map = new LinkedHashMap<>();
            map.put(I18n.get("export.user.username"), userDTO.getUsername());
            map.put(I18n.get("export.user.role"), String.join(",", roles));
            map.put(I18n.get("export.user.nickName"), userDTO.getNickName());
            map.put(I18n.get("export.user.sex"), I18n.isEn() ? userDTO.getSex().getEnName() : userDTO.getSex().getName());
            map.put(I18n.get("export.user.phone"), userDTO.getPhone());
            map.put(I18n.get("export.user.email"), userDTO.getEmail());
            map.put(I18n.get("export.user.status"), I18n.isEn() ? userDTO.getStatus().getEnName() : userDTO.getStatus().getName());
            map.put(I18n.get("export.user.loginTime"), userDTO.getLoginTime());
            map.put(I18n.get("export.user.loginCity"), userDTO.getLoginCity());
            map.put(I18n.get("export.createTime"), userDTO.getCreateTime());
            list.add(map);
        }
        ExcelUtil.downloadExcel(list);
    }

    /**
     * 用户合法性校验
     *
     * @param user 用户合法性校验
     */
    private void userLegitimacyVerification(User user) {
        long count;
        // 校验手机号是否存在
        if (StrUtil.isNotBlank(user.getPhone())) {
            count = count(new QueryWrapper<User>().eq("phone", user.getPhone()).ne(user.getId() != null, "id", user.getId()));
            if (count > 0 && StrUtil.isNotBlank(user.getPhone())) {
                throw new BizException("error.phoneExist");
            }
        }
        count = count(new QueryWrapper<User>().eq("username", user.getUsername()).ne(user.getId() != null, "id", user.getId()));
        if (count > 0 && StrUtil.isNotBlank(user.getUsername())) {
            throw new BizException("error.usernameExist");
        }
        count = count(new QueryWrapper<User>().eq("email", user.getEmail()).ne(user.getId() != null, "id", user.getId()));
        if (count > 0 && StrUtil.isNotBlank(user.getEmail())) {
            throw new BizException("error.emailExist");
        }
    }

}
