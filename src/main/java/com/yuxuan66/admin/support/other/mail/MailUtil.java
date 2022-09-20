package com.yuxuan66.admin.support.other.mail;

import cn.hutool.extra.mail.Mail;
import cn.hutool.extra.mail.MailAccount;
import com.yuxuan66.admin.cache.CacheKey;
import com.yuxuan66.admin.cache.ConfigKit;
import com.yuxuan66.admin.support.exception.BizException;

/**
 * @author Sir丶雨轩
 * @since 2022/9/20
 */
public class MailUtil {

    /**
     * 获取邮件发送账户
     *
     * @return 账户
     */
    private static MailAccount getAccount() {
        MailAccount mailAccount = new MailAccount();
        mailAccount.setAuth(true);
        mailAccount.setSslEnable(true);
        mailAccount.setUser(ConfigKit.get(CacheKey.CONFIG_MAIL_USER));
        mailAccount.setFrom(ConfigKit.get(CacheKey.CONFIG_MAIL_FROM));
        mailAccount.setPass(ConfigKit.get(CacheKey.CONFIG_MAIL_PASS));
        mailAccount.setHost(ConfigKit.get(CacheKey.CONFIG_MAIL_HOST));
        mailAccount.setPort(ConfigKit.get(CacheKey.CONFIG_MAIL_PORT, Integer.class));
        return mailAccount;
    }


    /**
     * 发送邮件
     */
    public static void send(String receiver, String title, String content) {
        try {
            Mail.create(getAccount())
                    .setTos(receiver)
                    .setTitle(title)
                    .setContent(content)
                    .setHtml(true)
                    .send();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("error.mailServer");
        }
    }

}
