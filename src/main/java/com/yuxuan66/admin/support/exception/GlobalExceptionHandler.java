package com.yuxuan66.admin.support.exception;

import cn.dev33.satoken.exception.SaTokenException;
import cn.hutool.core.convert.Convert;
import cn.hutool.crypto.CryptoException;
import cn.hutool.system.SystemUtil;
import com.yuxuan66.admin.common.i18n.I18n;
import com.yuxuan66.admin.support.base.resp.Rs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 全局异常处理，返回友好的提示
 * TODO 需要添加日志系统，保存错误日志
 *
 * @author Sir丶雨轩
 * @since 2022/9/8
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 接口不存在提示
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Rs> handleResourceNotFoundException(NoHandlerFoundException e) {
        return buildResponseEntity(Rs.error(I18n.get("error.apiNotFound",e.getRequestURL())));
    }

    /**
     * 账号相关异常
     *
     * @param e SaToken框架异常
     * @return 错误提示
     */
    @ExceptionHandler(SaTokenException.class)
    public ResponseEntity<Rs> handleSaTokenException(SaTokenException e) {
        return buildResponseEntity(Rs.notLogin(e.getMessage().replace("token", "账号")));
    }

    /**
     * rsa解密错误
     *
     * @param e 解密错误
     * @return 错误提示
     */
    @ExceptionHandler(CryptoException.class)
    public ResponseEntity<Rs> handleBadPaddingException(CryptoException e) {
        return buildResponseEntity(Rs.error(I18n.get("error.rsa")));
    }

    /**
     * 处理所有接口数据验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Rs> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return buildResponseEntity(Rs.error(message));
    }

    /**
     * 统一返回
     */
    private ResponseEntity<Rs> buildResponseEntity(Rs respEntity) {
        return new ResponseEntity<>(respEntity, HttpStatus.OK);
    }

    /**
     * 处理所有不可知的异常
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Rs> handleException(Throwable e) {
        e.printStackTrace();
        String message = Convert.toStr(e.getMessage(), I18n.get("error.500"));


        if (e.getClass().equals(BizException.class)) {
            return buildResponseEntity(Rs.error(e.getMessage()));
        }

        if (message.contains("Error updating database")) {
            return buildResponseEntity(Rs.error(I18n.get("error.dbExecute")));
        }

        return buildResponseEntity(Rs.error((SystemUtil.getOsInfo().isWindows() ? "异常类型： " + e.getClass().getSimpleName() + " ,错误信息：" + Convert.toStr(e.getMessage(), "暂无") : I18n.get("error.500"))));
    }
}
