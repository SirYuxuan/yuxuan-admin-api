package com.yuxuan66.admin.common.i18n;

import com.yuxuan66.admin.cache.StaticComponent;
import com.yuxuan66.admin.common.utils.WebUtil;
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

    /**
     * 当前上下文是否是英文环境
     *
     * @return 是否是英文
     */
    public static boolean isEn() {
        return "en".equalsIgnoreCase(WebUtil.getRequest().getHeader("lang"));
    }

    /**
     * 通过国际化获取是否
     * @param tag 是否
     * @return 是否
     */
    public static String yesOrNo(boolean tag) {
        return get(tag ? "public.yes" : "public.no");
    }
}
