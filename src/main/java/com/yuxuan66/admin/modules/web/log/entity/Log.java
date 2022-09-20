package com.yuxuan66.admin.modules.web.log.entity;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yuxuan66.admin.support.base.entity.BaseLogEntity;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 系统-日志表(SysLog)实体类
 *
 * @author Sir丶雨轩
 * @since 2022-09-16 16:49:14
 */
@Data
@TableName("sys_log")
public class Log extends BaseLogEntity<Log> implements Serializable {
    @Serial
    private static final long serialVersionUID = -13617873703287063L;

    /**
     * 日志标题
     */
    private String title;
    private String enTitle;

    /**
     * 请求耗时
     */
    private Long time;
    /**
     * 请求参数
     */
    private String param;
    /**
     * 请求返回结果
     */
    private String body;
    /**
     * 执行类
     */
    private String clazz;
    /**
     * 执行方法
     */
    private String method;



    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}

