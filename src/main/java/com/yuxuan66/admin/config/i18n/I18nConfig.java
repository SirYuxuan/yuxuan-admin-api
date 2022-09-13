package com.yuxuan66.admin.config.i18n;

import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.spi.resourceloading.ResourceBundleLocator;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.LocaleResolver;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ResourceBundle;

/**
 * 国际化相关配置
 *
 * @author Sir丶雨轩
 * @since 2022/9/13
 */
@Configuration
public class I18nConfig {

    /**
     * 通过header来控制国际化
     */
    @Bean
    public LocaleResolver localeResolver() {
        return new LangLocalResolver();
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setBasename("i18n/messages");
        resourceBundleMessageSource.setDefaultEncoding("UTF-8");
        return resourceBundleMessageSource;
    }


    /**
     * Validation message i18n
     *
     * @return Validator
     */
    @Bean
    public Validator getMyValidator() {
        //创建ResourceBundle定位器，从哪里读取配置文件
        ResourceBundleLocator resourceBundleLocator = locale -> ResourceBundle.getBundle("i18n/messages", locale);
        //定义消息interpolator，从ResourceBundle中读取
        ResourceBundleMessageInterpolator interpolator = new ResourceBundleMessageInterpolator(
                resourceBundleLocator);
        return Validation.byProvider(HibernateValidator.class)
                .configure()
                .failFast(true) //有一个失败就停止检查
                .messageInterpolator(interpolator)
                .buildValidatorFactory()
                .getValidator();
    }

    /**
     * 指定请求验证器
     *
     * @return MethodValidationPostProcessor
     */
    @Bean
    public MethodValidationPostProcessor validationPostProcessor() {
        MethodValidationPostProcessor processor = new MethodValidationPostProcessor();
        //指定请求验证器
        processor.setValidator(getMyValidator());
        return processor;
    }
}
