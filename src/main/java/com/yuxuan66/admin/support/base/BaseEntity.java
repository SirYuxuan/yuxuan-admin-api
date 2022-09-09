package com.yuxuan66.admin.support.base;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Sir丶雨轩
 * @since 2022/9/8
 */
@Data
public class BaseEntity<T extends Model<?>> extends Model<T>{

    @TableId
    private Long id;

    /**
     * 创建时间
     */
    private Timestamp createTime;
    /**
     * 创建人
     */
    private Long createId;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 更新时间
     */
    private Timestamp updateTime;
    /**
     * 更新人
     */
    private Long updateId;
    /**
     * 更新人
     */
    private String updateBy;
}