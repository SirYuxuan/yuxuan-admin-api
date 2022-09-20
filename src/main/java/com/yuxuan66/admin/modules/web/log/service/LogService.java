package com.yuxuan66.admin.modules.web.log.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuxuan66.admin.common.utils.Stp;
import com.yuxuan66.admin.modules.web.log.entity.Log;
import com.yuxuan66.admin.modules.web.log.mapper.LogMapper;
import com.yuxuan66.admin.support.base.BaseQuery;
import com.yuxuan66.admin.support.base.BaseService;
import com.yuxuan66.admin.support.base.resp.Ps;
import org.springframework.stereotype.Service;

/**
 * @author Sir丶雨轩
 * @since 2022/9/16
 */
@Service
public class LogService extends BaseService<Log, LogMapper> {

    /**
     * 分页查询我的操作日志
     * @param logQuery 查询条件
     * @return 查询日志列表
     */
    public Ps list(BaseQuery<Log> logQuery){
        logQuery.processingCreateTime();
        QueryWrapper<Log> queryWrapper = logQuery.getQueryWrapper();
        queryWrapper.eq("create_id", Stp.getId());
        return Ps.ok(baseMapper.selectPage(logQuery.getPage(),queryWrapper));
    }

}
