package com.zsh.cloud.auth.handler;

import com.zsh.cloud.common.core.domain.Result;
import com.zsh.cloud.common.core.exception.code.enums.GlobalErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 认证 服务 异常处理.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/29 09:57
 */
@Slf4j
@Component
@RestControllerAdvice
public class AuthExceptionHandler {
    
    @ExceptionHandler(value = InvalidGrantException.class)
    public Result<?> invalidGrantException(InvalidGrantException ex) {
        log.warn("[invalidGrantException]", ex);
        return Result.error(GlobalErrorCode.AUTHENTICATION_FAILED.getCode(), "用户名或密码不正确");
    }
    
    @ExceptionHandler(value = InternalAuthenticationServiceException.class)
    public Result<?> internalAuthenticationServiceException(InternalAuthenticationServiceException ex) {
        log.warn("[InternalAuthenticationServiceException]", ex);
        return Result.error(GlobalErrorCode.AUTHENTICATION_FAILED.getCode(), "用户名或密码不正确");
    }
    
    @ExceptionHandler(value = UsernameNotFoundException.class)
    public Result<?> usernameNotFoundException(UsernameNotFoundException ex) {
        log.warn("[UsernameNotFoundException]", ex);
        return Result.error(GlobalErrorCode.AUTHENTICATION_FAILED.getCode(), "用户名或密码不正确");
    }
    
    @ExceptionHandler(value = InvalidTokenException.class)
    public Result<?> invalidTokenException(InvalidTokenException ex) {
        log.warn("[InvalidTokenException]", ex);
        return Result.error(GlobalErrorCode.TOKEN_INVALID);
    }
}
