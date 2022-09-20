package com.yuxuan66.admin.modules.web.log.rest;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.yuxuan66.admin.modules.web.log.entity.Log;
import com.yuxuan66.admin.modules.web.log.service.LogService;
import com.yuxuan66.admin.support.base.BaseController;
import com.yuxuan66.admin.support.base.BaseQuery;
import com.yuxuan66.admin.support.base.resp.Ps;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sir丶雨轩
 * @since 2022/9/20
 */
@SaCheckLogin
@RestController
@RequestMapping(path = "/log")
@RequiredArgsConstructor
public class LogController extends BaseController<LogService> {


    /**
     * 分页查询我的操作日志
     * @param logBasicQuery 查询条件
     * @return 查询日志列表
     */
    @GetMapping
    private Ps list(BaseQuery<Log> logBasicQuery) {
        return baseService.list(logBasicQuery);
    }
}
