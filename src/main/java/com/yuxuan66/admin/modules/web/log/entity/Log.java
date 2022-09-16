package com.yuxuan66.admin.modules.web.log.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 系统-日志表(SysLog)实体类
 *
 * @author Sir丶雨轩
 * @since 2022-09-16 16:49:14
 */
@Data
@TableName("sys_log")
public class Log extends Model<Log> implements Serializable {
    @Serial
    private static final long serialVersionUID = -13617873703287063L;
    
    private Long id;
    /**
     * 日志标题
     */
    private String title;
    /**
     * 请求IP
     */
    private String ip;
    /**
     * 请求地址
     */
    private String city;
    /**
     * 浏览器
     */
    private String browser;
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
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Timestamp createTime;
    /**
     * 创建ID
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createId;
    /**
     * 创建人名称
     */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;



}

