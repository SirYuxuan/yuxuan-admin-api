package com.yuxuan66.admin.support.base;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * @author Sir丶雨轩
 * @since 2022/9/16
 */
@Data
public class BaseQuery<T> {

    /**
     * 模糊查询字段
     */
    private String blurry;
    /**
     * 分页 页码
     */
    private long page;
    /**
     * 分页 每页大小
     */
    private long size;

    /**
     * 手动分页-开始条数
     */
    private long limitStart;

    /**
     * 禁止外部修改分页参数
     */
    private void setLimitStart(Long limitStart) {
    }

    /**
     * 排序字段
     */
    private String order;
    /**
     * 排序类型
     */
    private String orderBy;

    /**
     * 时间查询
     */
    private String[] createTime;

    public BaseQuery() {
        this.limitStart = (page) * size;
        this.processingSort();
    }

    private final QueryWrapper<T> queryWrapper = new QueryWrapper<>();

    /**
     * 获取MyBatis的分页对象
     *
     * @return 分页对象
     */
    public Page<T> getPage() {
        return new Page<T>(page + 1, size);
    }

    /**
     * 处理分页参数
     */
    public void processingSort() {
        if (StrUtil.isNotBlank(order) && StrUtil.isNotBlank(orderBy)) {
            if (orderBy.contains("desc")) {
                queryWrapper.orderByDesc(StrUtil.toUnderlineCase(order));
            } else {
                queryWrapper.orderByAsc(StrUtil.toUnderlineCase(order));
            }
        } else {
            queryWrapper.orderByDesc("create_time");
        }
    }

    /**
     * 处理模糊查询数据
     *
     * @param params 字段
     */
    public void processingBlurry(String... params) {
        if (StrUtil.isNotBlank(this.getBlurry())) {
            queryWrapper.and(tQueryWrapper -> {
                for (int i = 0; i < params.length; i++) {
                    tQueryWrapper.like(params[i], blurry);
                    if (i != params.length - 1) {
                        tQueryWrapper.or();
                    }
                }
            });
        }
    }

    /**
     * 根据指定时间字段查询
     *
     * @param field 字段
     */
    public void processingCreateTime(String field) {
        if (this.getCreateTime() != null && this.getCreateTime().length == 2) {
            queryWrapper.ge(field, this.getCreateTime()[0]);
            queryWrapper.le(field, this.getCreateTime()[1]);
        }
    }

    /**
     * 处理创建时间
     */
    public void processingCreateTime() {
        processingCreateTime("create_time");
    }

}
