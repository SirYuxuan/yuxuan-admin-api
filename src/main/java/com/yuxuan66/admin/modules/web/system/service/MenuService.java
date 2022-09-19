package com.yuxuan66.admin.modules.web.system.service;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuxuan66.admin.cache.CacheKey;
import com.yuxuan66.admin.common.consts.MenuType;
import com.yuxuan66.admin.common.i18n.I18n;
import com.yuxuan66.admin.common.utils.ExcelUtil;
import com.yuxuan66.admin.common.utils.Stp;
import com.yuxuan66.admin.common.utils.tree.TreeUtil;
import com.yuxuan66.admin.modules.web.system.entity.Menu;
import com.yuxuan66.admin.modules.web.system.entity.query.MenuQuery;
import com.yuxuan66.admin.modules.web.system.mapper.MenuMapper;
import com.yuxuan66.admin.modules.web.system.mapper.UserMapper;
import com.yuxuan66.admin.support.base.BaseService;
import com.yuxuan66.admin.support.base.resp.Ps;
import com.yuxuan66.admin.support.base.resp.Rs;
import com.yuxuan66.admin.support.exception.BizException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

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
        return Rs.ok(new TreeUtil<Menu>().menuList(userService.getUserById(Stp.getId()).getMenus().stream().filter(item -> item.getType() != MenuType.BUTTON).toList()));
    }

    /**
     * 查询菜单列表
     *
     * @param menuQuery 查询参数
     * @return 菜单列表
     */
    public Ps list(MenuQuery menuQuery) {

        if (menuQuery.getAll()) {
            return Ps.ok(new TreeUtil<Menu>().menuList(baseMapper.selectList(null)));
        }
        menuQuery.processingCreateTime();
        QueryWrapper<Menu> queryWrapper = menuQuery.getQueryWrapper();

        if (!menuQuery.getAll()) {
            queryWrapper.eq("pid", menuQuery.getPid());
        }

        List<Menu> menuList = baseMapper.selectList(queryWrapper);

        for (Menu menu : menuList) {
            menu.setHasChildrenData(menu.getHasChildren() == null || !menu.getHasChildren());
        }
        return Ps.ok(menuList);
    }


    /**
     * 递归获取所有子菜单
     *
     * @param menuList 菜单列表
     * @param menuSet  菜单集合
     * @param id       用来缓存的Key
     * @return 菜单集合
     */
    @Cacheable(value = CacheKey.SERVICE_MENU, key = "'ChildMenus:' + #id")
    public Set<Menu> getChildMenus(List<Menu> menuList, Set<Menu> menuSet, Long id) {
        for (Menu menu : menuList) {
            menuSet.add(menu);
            List<Menu> menus = baseMapper.selectList(new QueryWrapper<Menu>().eq("pid", menu.getId()));
            if (menus != null && menus.size() != 0) {
                getChildMenus(menus, menuSet, id);
            }
        }
        return menuSet;
    }

    /**
     * 根据ID获取同级与上级数据
     *
     * @param menu     菜单
     * @param menuList 菜单列表
     * @return 菜单列表
     */
    @Cacheable(value = CacheKey.SERVICE_MENU, key = "'Superior:' + #id")
    public List<Menu> getSuperior(Menu menu, List<Menu> menuList, Long id) {

        if (menu.getPid() == 0) {
            menuList.addAll(baseMapper.selectList(new QueryWrapper<Menu>().eq("pid", 0)));
            return menuList;
        }
        menuList.addAll(baseMapper.selectList(new QueryWrapper<Menu>().eq("pid", menu.getPid())));

        return getSuperior(baseMapper.selectById(menu.getPid()), menuList, id);
    }

    /**
     * 根据id获取菜单数据
     *
     * @param id 菜单id
     * @return 菜单
     */
    @Cacheable(value = CacheKey.SERVICE_MENU, key = "#id")
    public Menu getById(Long id) {
        return baseMapper.selectById(id);
    }

    /**
     * 获取指定pid下的菜单
     *
     * @param pid pid
     * @return 菜单
     */
    @Cacheable(value = CacheKey.SERVICE_MENU, key = "'Child:' + #pid")
    public List<Menu> getMenus(Long pid) {
        List<Menu> menus;
        if (pid != null && !pid.equals(0L)) {
            menus = baseMapper.selectList(new QueryWrapper<Menu>().eq("pid", pid));
        } else {
            menus = baseMapper.selectList(new QueryWrapper<Menu>().eq("pid", 0));
        }
        return menus;
    }

    /**
     * 添加一个新的菜单
     *
     * @param menu 菜单
     * @return 标准返回
     */
    @CacheEvict(value = CacheKey.SERVICE_MENU, allEntries = true)
    public Rs add(Menu menu) {

        if (menu.getPid() == 0) {
            menu.setPid(0L);
        }
        menu.setHasChildren(false);
        if (menu.getType() == MenuType.CATALOG && !menu.getIsLink()) {
            menu.setComponent("Layout");
        }
        if (menu.getType() == MenuType.CATALOG && !menu.getPath().startsWith("/")) {
            menu.setPath("/" + menu.getPath());
        }
        menu.insert();
        if (Convert.toLong(menu.getPid(), 0L) > 0) {
            // 给这个ID所在的数据修改子集
            long count = count(new QueryWrapper<Menu>().eq("pid", menu.getPid()));
            Menu resource = new Menu();
            resource.setId(menu.getPid());
            resource.setHasChildren(count > 0);
            baseMapper.updateById(resource);
        }
        return Rs.ok();
    }

    /**
     * 批量删除菜单列表
     *
     * @param ids id列表
     * @return 标准返回
     */
    @CacheEvict(value = CacheKey.SERVICE_MENU, allEntries = true)
    public Rs del(Set<Long> ids) {

        for (Long id : ids) {
            Menu menu = super.getById(id);
            removeById(id);
            handleParentMenu(menu);
            remove(new QueryWrapper<Menu>().eq("pid", id));
        }
        return Rs.ok();
    }

    /**
     * 编辑一个菜单 <br/>
     * <code>
     * condition = "#result != #resource.pid"
     * 如果老的pid！=新的pid，说明菜单层级被编辑，所以需要清除缓存
     * </code>
     *
     * @param resource 菜单
     * @return 标准返回
     */
    @CacheEvict(value = CacheKey.SERVICE_MENU, allEntries = true, condition = "#result != #resource.pid")
    public Long edit(Menu resource) {

        if (resource.getId().equals(resource.getPid())) {
            throw new BizException("error.notParentSelf");
        }

        Menu oldMenu = super.getById(resource.getId());
        long oldMenuPid = oldMenu.getPid();

        updateById(resource);

        handleParentMenu(resource);
        handleParentMenu(oldMenu);

        return oldMenuPid;
    }

    /**
     * 处理父级id的是否有子集
     *
     * @param menu 父级id
     */
    public void handleParentMenu(Menu menu) {

        if (menu.getPid() != null) {
            Menu resource = new Menu();
            resource.setId(menu.getPid());
            resource.setHasChildren(count(new QueryWrapper<Menu>().eq("pid", menu.getPid())) > 0);
            updateById(resource);
        }
    }

    /**
     * 导出菜单
     *
     * @param menuQuery 查询条件
     * @throws IOException io异常
     */
    public void download(MenuQuery menuQuery) throws IOException {

        menuQuery.setAll(true);

        menuQuery.processingCreateTime();
        QueryWrapper<Menu> queryWrapper = menuQuery.getQueryWrapper();

        List<Menu> menuList = list(queryWrapper);

        List<Map<String, Object>> list = new ArrayList<>();
        for (Menu menuDTO : menuList) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put(I18n.get("export.menu.title"), I18n.isEn() ? menuDTO.getEnTitle() : menuDTO.getTitle());
            map.put(I18n.get("export.menu.type"), menuDTO.getType());
            map.put(I18n.get("export.menu.permission"), menuDTO.getPermission());
            map.put(I18n.get("export.menu.linkMenu"), I18n.yesOrNo(Convert.toBool(menuDTO.getIsLink(), false)));
            map.put(I18n.get("export.menu.visible"), I18n.yesOrNo(Convert.toBool(menuDTO.getHidden(), false)));
            map.put(I18n.get("export.menu.cache"), I18n.yesOrNo(!Convert.toBool(menuDTO.getCache(), true)));
            map.put(I18n.get("export.createTime"), menuDTO.getCreateTime());
            list.add(map);
        }
        ExcelUtil.downloadExcel(list);
    }


}
