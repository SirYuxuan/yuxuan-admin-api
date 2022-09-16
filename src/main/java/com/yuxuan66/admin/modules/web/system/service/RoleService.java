package com.yuxuan66.admin.modules.web.system.service;

import com.yuxuan66.admin.modules.web.system.entity.Role;
import com.yuxuan66.admin.modules.web.system.mapper.RoleMapper;
import com.yuxuan66.admin.support.base.BaseService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色操作
 * @author Sir丶雨轩
 * @since 2022/9/16
 */
@Service
public class RoleService extends BaseService<Role, RoleMapper> {

    /**
     * 查询系统全部角色
     * @return 全部角色
     */
    @Cacheable(value = "Cache:Role",key = "'All'")
    public List<Role> queryAll(){
        return query().list();
    }

}
