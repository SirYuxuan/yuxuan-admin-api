package com.yuxuan66.admin.support.credential;

/**
 * 凭证接口
 * @author Sir丶雨轩
 * @since 2022/9/20
 */
public interface Credential<T> {

    /**
     * 获取指定类型的凭证
     * @return 凭证
     */
    T get();
}
