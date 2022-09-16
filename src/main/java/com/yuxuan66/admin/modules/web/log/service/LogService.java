package com.yuxuan66.admin.modules.web.log.service;

import com.yuxuan66.admin.modules.web.log.entity.Log;
import com.yuxuan66.admin.modules.web.log.mapper.LogMapper;
import com.yuxuan66.admin.support.base.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author Sir丶雨轩
 * @since 2022/9/16
 */
@Service
public class LogService extends BaseService<Log, LogMapper> {

    @PostConstruct
    public void sss(){
        Log log1 = new Log();
        log1.insert();
        Log log2 = new Log();
        log2.insert();
    }

}
