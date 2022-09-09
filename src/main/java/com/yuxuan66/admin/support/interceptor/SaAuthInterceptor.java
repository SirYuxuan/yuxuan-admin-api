package com.yuxuan66.admin.support.interceptor;

import cn.dev33.satoken.interceptor.SaAnnotationInterceptor;
import cn.dev33.satoken.strategy.SaStrategy;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author Sir丶雨轩
 * @since 2022/9/8
 */
public class SaAuthInterceptor extends SaAnnotationInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 获取处理method
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        Method method = ((HandlerMethod) handler).getMethod();

        // 进行验证
        SaStrategy.me.checkMethodAnnotation.accept(method);

        // 通过验证
        return true;
    }
}
