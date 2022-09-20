package com.yuxuan66.admin.support.credential.aliyun;

import cn.hutool.core.lang.Singleton;
import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.yuxuan66.admin.cache.CacheKey;
import com.yuxuan66.admin.cache.ConfigKit;

/**
 * 阿里云凭证管理
 *
 * @author Sir丶雨轩
 * @since 2022/9/20
 */
public class AliCredential<T> implements com.yuxuan66.admin.support.credential.Credential<T> {

    private final T data;

    @SuppressWarnings("all")
    public AliCredential() {
        this.data = (T) StaticCredentialProvider.create(Credential.builder()
                .accessKeyId(ConfigKit.get(CacheKey.CONFIG_CREDENTIAL_ALI_ACCESS_KEY_ID))
                .accessKeySecret(ConfigKit.get(CacheKey.CONFIG_CREDENTIAL_ALI_ACCESS_KEY_SECRET))
                .build());
    }

    /**
     * 获取自身的单例实例
     * @return 实例
     */
    @SuppressWarnings("all")
    public static <T> AliCredential<T> getInstance(Class<T> clazz){
        return Singleton.get(AliCredential.class);
    }

    /**
     * 获取凭证
     *
     * @return 阿里云凭证
     */
    public T get() {
        return data;
    }
}
