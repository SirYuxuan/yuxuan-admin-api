package com.yuxuan66.admin.support.other.phone.aliyun;

import cn.hutool.core.lang.Singleton;
import com.alibaba.fastjson.JSON;
import com.aliyun.auth.credentials.provider.ICredentialProvider;
import com.aliyun.sdk.service.dysmsapi20170525.AsyncClient;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsResponse;
import com.yuxuan66.admin.support.credential.aliyun.AliCredential;
import com.yuxuan66.admin.support.exception.BizException;
import com.yuxuan66.admin.support.other.phone.PhoneUtil;
import darabonba.core.client.ClientOverrideConfiguration;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

/**
 * @author Sir丶雨轩
 * @since 2022/9/20
 */
@Slf4j
public class AliPhoneUtil implements PhoneUtil {

    /**
     * 身份验证码 代码模板
     */
    public static final String SMS_CODE_AUTHENTICATION = "SMS_5074309";

    private final AsyncClient client;

    public AliPhoneUtil() {
        client = AsyncClient.builder()
                .region("cn-beijing")
                .credentialsProvider(AliCredential.getInstance(ICredentialProvider.class).get())
                .overrideConfiguration(
                        ClientOverrideConfiguration.create()
                                .setEndpointOverride("dysmsapi.aliyuncs.com")
                )
                .build();
    }

    /**
     * 获取自身的单例实例
     *
     * @return 实例
     */
    @SuppressWarnings("all")
    public static AliPhoneUtil getInstance() {
        return Singleton.get(AliPhoneUtil.class);
    }

    /**
     * 发送验证码
     *
     * @param phone    手机号
     * @param template 模板ID
     * @param param    替换参数
     */
    @SneakyThrows
    public void send(String phone, String template, String param) {

        SendSmsRequest sendSmsRequest = SendSmsRequest.builder()
                .signName("雨轩网络科技")
                .phoneNumbers(phone)
                .templateCode(template)
                .templateParam(param)
                .build();

        CompletableFuture<SendSmsResponse> response = client.sendSms(sendSmsRequest);
        SendSmsResponse resp = response.get();
        if(!"OK".equals(resp.getBody().getCode())){
            log.warn("验证码发送失败：" + JSON.toJSONString(resp.getBody()));
            throw new BizException("error.sms");
        }
    }
}
