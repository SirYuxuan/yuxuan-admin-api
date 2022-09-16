package com.yuxuan66.admin.modules.web.system.mapper;

import com.yuxuan66.admin.modules.web.system.entity.User;
import com.yuxuan66.admin.modules.web.system.entity.query.UserQuery;
import com.yuxuan66.admin.support.base.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户操作
 * @author Sir丶雨轩
 * @since 2022/9/8
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 通过用户id查询用户详细信息，包含角色、菜单、部门、头像等
     *
     * @param userId 用户id
     * @return 用户详情
     */
    User findUserById(Long userId);

    /**
     * 分页查询用户列表
     *
     * @param userQuery 查询参数
     * @return 用户列表
     */
    List<User> listUser(@Param("query") UserQuery userQuery);

    /**
     * 计算用户总条数
     * @param userQuery 查询参数
     * @return 条数
     */
    long countUser(@Param("query") UserQuery userQuery);
}
