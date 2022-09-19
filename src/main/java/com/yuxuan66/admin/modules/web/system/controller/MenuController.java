package com.yuxuan66.admin.modules.web.system.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.yuxuan66.admin.common.utils.tree.TreeUtil;
import com.yuxuan66.admin.modules.web.system.entity.Menu;
import com.yuxuan66.admin.modules.web.system.entity.query.MenuQuery;
import com.yuxuan66.admin.modules.web.system.service.MenuService;
import com.yuxuan66.admin.support.base.BaseController;
import com.yuxuan66.admin.support.base.resp.Ps;
import com.yuxuan66.admin.support.base.resp.Rs;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Sir丶雨轩
 * @since 2022/9/16
 */
@SaCheckLogin
@RestController
@RequestMapping(path = "/menu")
@RequiredArgsConstructor
public class MenuController extends BaseController<MenuService> {

    /**
     * 构建左侧菜单
     * 移除掉所有按钮级菜单
     *
     * @return 菜单
     */
    @GetMapping(path = "/build")
    public Rs build() {
        return baseService.build();
    }


    /**
     * 查询菜单列表
     *
     * @param menuQuery 查询参数
     * @return 菜单列表
     */
    @GetMapping
    public Ps list(MenuQuery menuQuery) {
        return baseService.list(menuQuery);
    }

    /**
     * 获取自身及子集的id
     *
     * @param id 菜单id
     * @return id列表
     */
    @GetMapping(path = "/child")
    public Rs child(Long id) {
        Set<Menu> menuSet = new HashSet<>();
        List<Menu> menuList = baseService.getMenus(id);
        menuSet.add(baseService.getById(id));
        menuSet = baseService.getChildMenus(menuList, menuSet, id);
        return Rs.ok(menuSet.stream().map(Menu::getId).collect(Collectors.toSet()));
    }

    /**
     * 查询菜单树，根据父id，查询所有上级
     * @param id 父id
     * @return 部门数
     */
    @GetMapping(path = "/queryMenuTree")
    public Rs queryMenuTree(Long id){
        Menu deptDto = baseService.getById(id);
        List<Menu> deptList = baseService.getSuperior(deptDto, new ArrayList<>(),id);
        return Rs.ok(new TreeUtil<Menu>().menuList(deptList));
    }

    /**
     * 添加一个新的菜单
     *
     * @param menu 菜单
     * @return 标准返回
     */
    @PostMapping
    public Rs add(@RequestBody Menu menu){
        return baseService.add(menu);
    }

    /**
     * 编辑一个菜单
     * @param menu 菜单
     * @return 标准返回
     */
    @PutMapping
    public Rs edit(@RequestBody Menu menu){
        baseService.edit(menu);
        return Rs.ok();
    }

    /**
     * 批量删除菜单列表
     *
     * @param ids id列表
     * @return 标准返回
     */
    @DeleteMapping
    public Rs del(@RequestBody Set<Long> ids){
        return baseService.del(ids);
    }


    /**
     * 导出菜单列表
     * @param menuQuery 查询条件
     * @throws IOException IOException
     */
    @GetMapping(path = "/download")
    public void download(MenuQuery menuQuery) throws IOException {
        baseService.download(menuQuery);
    }

}
