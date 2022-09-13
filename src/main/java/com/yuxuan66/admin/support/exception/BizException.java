package com.yuxuan66.admin.support.exception;

import com.yuxuan66.admin.common.i18n.I18n;

/**
 * @author Sir丶雨轩
 * @since 2022/9/13
 */
public class BizException extends RuntimeException{

    public BizException(String key) {
        super(I18n.get(key));
    }
}
