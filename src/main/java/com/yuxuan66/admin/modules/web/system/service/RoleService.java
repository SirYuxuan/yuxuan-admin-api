package com.yuxuan66.admin.modules.web.system.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuxuan66.admin.cache.CacheKey;
import com.yuxuan66.admin.common.i18n.I18n;
import com.yuxuan66.admin.common.utils.ExcelUtil;
import com.yuxuan66.admin.modules.web.system.entity.Role;
import com.yuxuan66.admin.modules.web.system.entity.RolesMenus;
import com.yuxuan66.admin.modules.web.system.mapper.RoleMapper;
import com.yuxuan66.admin.modules.web.system.mapper.RolesMenusMapper;
import com.yuxuan66.admin.support.base.BaseQuery;
import com.yuxuan66.admin.support.base.BaseService;
import com.yuxuan66.admin.support.base.resp.Ps;
import com.yuxuan66.admin.support.base.resp.Rs;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

/**
 * 角色操作
 *
 * @author Sir丶雨轩
 * @since 2022/9/16
 */
@Service
public class RoleService extends BaseService<Role, RoleMapper> {

    @Resource
    private RolesMenusMapper rolesMenusMapper;

    /**
     * 查询系统全部角色
     *
     * @return 全部角色
     */
    @Cacheable(value = CacheKey.SERVICE_ROLE, key = "'All'")
    public List<Role> queryAll() {
        return query().list();
    }

    /**
     * 查询角色列表
     *
     * @param roleQuery 查询参数
     * @return 角色列表
     */
    public Ps list(BaseQuery<Role> roleQuery) {
        return Ps.ok(baseMapper.countRole(roleQuery), baseMapper.listRole(roleQuery));
    }

    /**
     * 编辑一个角色的菜单,由于角色权限变动，删除所有用户及角色缓存
     *
     * @param role 角色信息->菜单
     * @return 标准返回
     */
    @Caching(
            evict = {
                    @CacheEvict(value = CacheKey.SERVICE_USER, allEntries = true),
                    @CacheEvict(value = CacheKey.SERVICE_ROLE, allEntries = true),
            }
    )
    public Rs editMenu(Role role) {
        rolesMenusMapper.delete(new QueryWrapper<RolesMenus>().eq("role_id", role.getId()));
        if(role.getMenus() != null && !role.getMenus().isEmpty()){
            List<RolesMenus> rolesMenusList = new ArrayList<>();
            role.getMenus().forEach(item -> rolesMenusList.add(new RolesMenus(role.getId(), item.getId())));
            rolesMenusMapper.batchInsert(rolesMenusList);
        }
        return Rs.ok();
    }

    /**
     * 添加/修改一个的角色
     * @param role 角色
     * @return 标准返回
     */
    public Rs addOrEdit(Role role){
        role.insertOrUpdate();
        return Rs.ok();
    }


    /**
     * 删除一组角色
     * @param ids 角色id
     * @return 标准返回
     */
    @Caching(
            evict = {
                    @CacheEvict(value = CacheKey.SERVICE_USER, allEntries = true),
                    @CacheEvict(value = CacheKey.SERVICE_ROLE, allEntries = true),
            }
    )
    public Rs del(Set<Long> ids){
        removeBatchByIds(ids);
        rolesMenusMapper.delete(new QueryWrapper<RolesMenus>().in("role_id",ids));
        return Rs.ok();
    }

    /**
     * 导出角色列表
     * @param roleQuery 查询条件
     * @throws IOException io异常
     */
    public void download(BaseQuery<Role> roleQuery) throws IOException {

        List<Role> roleList = baseMapper.listRole(roleQuery);

        List<Map<String, Object>> list = new ArrayList<>();
        for (Role role : roleList) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put(I18n.get("export.role.roleName"), role.getName());
            map.put(I18n.get("export.role.describe"), role.getRemark());
            map.put(I18n.get("export.createTime"), role.getCreateTime());
            list.add(map);
        }
        ExcelUtil.downloadExcel(list);
    }

}
