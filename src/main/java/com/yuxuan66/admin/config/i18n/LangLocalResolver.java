package com.yuxuan66.admin.config.i18n;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * 通过header来控制国际化
 * @author Sir丶雨轩
 * @since 2022/9/13
 */
public class LangLocalResolver extends AcceptHeaderLocaleResolver implements WebMvcConfigurer {

    List<Locale> locales = Arrays.asList(new Locale("zh"), new Locale("en"));

    @NotNull
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String lang = request.getHeader("lang");
        return lang == null ? Locale.CHINESE : Locale.lookup(Locale.LanguageRange.parse(lang), locales);
    }
}
