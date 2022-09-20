package com.yuxuan66.admin.support.base;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Sir丶雨轩
 * @since 2022/9/8
 */
@SuppressWarnings("all")
@RequiredArgsConstructor
public class BaseController<S> {


    @Autowired
    protected S baseService;

}
