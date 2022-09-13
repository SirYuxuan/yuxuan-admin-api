package com.yuxuan66.admin.common.i18n;

import com.yuxuan66.admin.cache.StaticComponent;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * I18n操作对象
 *
 * @author Sir丶雨轩
 * @since 2022/9/13
 */
public final class I18n {

    /**
     * 获取国际化的值
     *
     * @param key    key
     * @param params 参数
     * @return 值
     */
    public static String get(String key, Object... params) {
        return StaticComponent.messageSource.getMessage(key, params, LocaleContextHolder.getLocale());
    }
}
