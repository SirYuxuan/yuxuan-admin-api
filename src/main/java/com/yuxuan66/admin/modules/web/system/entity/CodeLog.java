package com.yuxuan66.admin.modules.web.system.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yuxuan66.admin.common.consts.CodeLogAction;
import com.yuxuan66.admin.common.consts.CodeLogType;
import com.yuxuan66.admin.support.base.entity.BaseLogEntity;
import com.yuxuan66.admin.support.json.FastJsonEnumDeserializer;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 系统-验证码发送日志(SysCodeLog)实体类
 *
 * @author Sir丶雨轩
 * @since 2022-09-20 15:57:56
 */
@Data
@TableName("sys_code_log")
public class CodeLog extends BaseLogEntity<CodeLog> implements Serializable {
    @Serial
    private static final long serialVersionUID = -27374614013134453L;

    /***
     * 操作类型
     */
    private CodeLogAction action;
    /**
     * 接收者
     */
    private String receiver;
    /**
     * 发送的验证码
     */
    private String code;

    /**
     * 发送类型，0=邮箱验证码，1=短信验证码
     */
    @JSONField(deserializeUsing = FastJsonEnumDeserializer.class)
    private CodeLogType type;

    @TableField(exist = false)
    private Integer logType;
    @TableField(exist = false)
    private Integer actionType;


}

