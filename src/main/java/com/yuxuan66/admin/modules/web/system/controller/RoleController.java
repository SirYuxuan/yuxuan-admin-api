package com.yuxuan66.admin.modules.web.system.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.yuxuan66.admin.modules.web.system.entity.Role;
import com.yuxuan66.admin.modules.web.system.service.RoleService;
import com.yuxuan66.admin.support.base.BaseController;
import com.yuxuan66.admin.support.base.BaseQuery;
import com.yuxuan66.admin.support.base.resp.Ps;
import com.yuxuan66.admin.support.base.resp.Rs;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Set;

/**
 * @author Sir丶雨轩
 * @since 2022/9/16
 */
@SaCheckLogin
@RestController
@RequestMapping(path = "/role")
@RequiredArgsConstructor
public class RoleController extends BaseController<RoleService> {

    /**
     * 查询系统全部角色
     * @return 全部角色
     */
    @GetMapping(path = "/queryAll")
    public Rs queryAll(){
        return Rs.ok(baseService.queryAll());
    }


    /**
     * 查询角色列表
     *
     * @param roleQuery 查询参数
     * @return 角色列表
     */
    @GetMapping
    public Ps list(BaseQuery<Role> roleQuery){
        return baseService.list(roleQuery);
    }


    /**
     * 添加一个新的角色
     * @param role 角色
     * @return 标准返回
     */
    @PostMapping
    public Rs add(@RequestBody Role role){
        return baseService.addOrEdit(role);
    }

    /**
     * 修改一个角色
     * @param role 角色
     * @return 标准返回
     */
    @PutMapping
    public Rs edit(@RequestBody Role role){
        return baseService.addOrEdit(role);
    }
    /**
     * 删除一组角色
     * @param ids 角色id
     * @return 标准返回
     */
    @DeleteMapping
    public Rs del(@RequestBody Set<Long> ids){
        return baseService.del(ids);
    }

    /**
     * 编辑一个角色的菜单
     * @param role 角色信息->菜单
     * @return 标准返回
     */
    @PutMapping(path = "/editMenu")
    public Rs editMenu(@RequestBody Role role){
        return baseService.editMenu(role);
    }

    /**
     * 导出角色列表
     * @param roleQuery 查询条件
     * @throws IOException IOException
     */
    @GetMapping(path = "/download")
    public void download(BaseQuery<Role> roleQuery) throws IOException {
        baseService.download(roleQuery);
    }

}
