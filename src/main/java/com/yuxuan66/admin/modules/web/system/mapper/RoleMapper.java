package com.yuxuan66.admin.modules.web.system.mapper;

import com.yuxuan66.admin.modules.web.system.entity.Role;
import com.yuxuan66.admin.support.base.BaseMapper;
import com.yuxuan66.admin.support.base.BaseQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Sir丶雨轩
 * @since 2022/9/16
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 分页查询角色列表
     *
     * @param roleQuery 查询参数
     * @return 用户列表
     */
    List<Role> listRole(@Param("query") BaseQuery<Role> roleQuery);

    /**
     * 计算角色总条数
     * @param roleQuery 查询参数
     * @return 条数
     */
    long countRole(@Param("query") BaseQuery<Role> roleQuery);
}
