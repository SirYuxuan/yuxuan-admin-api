package com.yuxuan66.admin.modules.web.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yuxuan66.admin.common.consts.MenuType;
import com.yuxuan66.admin.common.utils.tree.TreeField;
import com.yuxuan66.admin.common.utils.tree.TreeId;
import com.yuxuan66.admin.support.base.BaseEntity;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 系统-菜单表(SysMenu)实体类
 *
 * @author Sir丶雨轩
 * @since 2022-09-16 10:11:13
 */
@Data
@TableName("sys_menu")
public class Menu extends BaseEntity<Menu> implements Serializable {

    @Serial
    private static final long serialVersionUID = 413567790353332932L;

    @TreeId
    @TreeField
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 菜单名称
     */
    @TreeField(value = "label", secondLevel = "meta")
    private String title;

    @TreeField(value = "enTitle", secondLevel = "meta")
    private String enTitle;

    @TreeField(value = "title", like = "title")
    @TableField(exist = false)
    private String treeTitle;
    /**
     * 父级ID
     */
    @TreeId(isParent = true)
    private Long pid = 0L;
    /**
     * 菜单类型 0=目录,1=菜单,2=按钮
     */
    @TreeField(isEnum = true)
    private MenuType type;
    /**
     * 菜单图标
     */
    @TreeField(secondLevel = "meta")
    private String icon;
    /**
     * 菜单图标
     */
    @TreeField(secondLevel = "meta")
    private String remixIcon;
    /**
     * 是否外链
     */
    @TreeField
    private Boolean isLink;
    /**
     * 是否缓存
     */
    @TreeField
    private Boolean cache;
    /**
     * 是否可见
     */
    @TreeField
    private Boolean hidden;
    /**
     * 权限字符串
     */
    private String permission;
    /**
     * 路由地址
     */
    @TreeField
    private String path;
    /**
     * 组件名称
     */
    @TreeField
    private String name;
    /**
     * 组件地址
     */
    @TreeField
    private String component;
    /**
     * 是否有子集
     */
    private Boolean hasChildren;
    /**
     * 排序号
     */
    private Integer sort;
    /**
     * 是否有徽章
     */
    @TreeField(secondLevel = "meta")
    private String badge;
    /**
     * 重定向地址
     */
    private String redirect;

    @TableField(exist = false)
    @TreeField(value = "hasChildrenData",like = "hasChildren")
    private Boolean hasChildrenData;


}

