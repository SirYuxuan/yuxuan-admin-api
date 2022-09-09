package com.yuxuan66.admin.support.base;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 基础Service
 *
 * @author Sir丶雨轩
 * @since 2022/9/8
 */
public class BaseService<T extends BaseEntity<T>, M extends BaseMapper<T>> extends ServiceImpl<M, T> {
}
