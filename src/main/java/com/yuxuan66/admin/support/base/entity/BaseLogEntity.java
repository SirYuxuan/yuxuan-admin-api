package com.yuxuan66.admin.support.base.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.yuxuan66.admin.common.utils.WebUtil;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Sir丶雨轩
 * @since 2022/9/20
 */
@Data
public class BaseLogEntity<T extends Model<?>> extends Model<T> {

    private Long id;

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
     * 客户端UA
     */
    private String ua;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Timestamp createTime;
    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createId;
    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    public void setInfo(){
        String userAgent = WebUtil.getRequest().getHeader("User-Agent");
        UserAgent ua = UserAgent.parseUserAgentString(userAgent);
        this.setUa(userAgent);
        this.setIp(WebUtil.getIp());
        this.setBrowser(ua.getBrowser().getName());
    }
}
