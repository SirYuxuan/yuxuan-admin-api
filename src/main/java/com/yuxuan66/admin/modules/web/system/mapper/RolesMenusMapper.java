package com.yuxuan66.admin.modules.web.system.mapper;

import com.yuxuan66.admin.modules.web.system.entity.RolesMenus;
import com.yuxuan66.admin.support.base.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Sir丶雨轩
 * @since 2022/9/19
 */
@Mapper
public interface RolesMenusMapper extends BaseMapper<RolesMenus> {

    /**
     * 批量添加用户角色关联
     * @param list 用户角色关联
     * @return 受影响行数
     */
    long batchInsert(List<RolesMenus> list);
}
