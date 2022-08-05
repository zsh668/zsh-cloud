package com.zsh.cloud.common.exception.handler;

import com.zsh.cloud.common.core.domain.Result;
import com.zsh.cloud.common.core.exception.BaseUncheckedException;
import com.zsh.cloud.common.core.exception.ServiceException;
import com.zsh.cloud.common.core.exception.code.enums.GlobalErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * 自定义异常处理器
 *
 * @author hang
 * @version 1.0
 * @date 2022/03/14 11:45
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    /**
     * 处理 SpringMVC 请求参数缺失.
     * </p>
     * 例如说，接口上设置了 @RequestParam("xx") 参数，结果并未传递 xx 参数
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public Result<?> missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException ex) {
        log.warn("[missingServletRequestParameterExceptionHandler]", ex);
        return Result.error(GlobalErrorCode.BAD_REQUEST.getCode(), String.format("请求参数缺失:%s", ex.getParameterName()));
    }
    
    /**
     * 处理 SpringMVC 请求参数类型错误.
     * </p>
     * 例如说，接口上设置了 @RequestParam("xx") 参数为 Integer，结果传递 xx 参数类型为 String
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result<?> methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException ex) {
        log.warn("[missingServletRequestParameterExceptionHandler]", ex);
        return Result.error(GlobalErrorCode.BAD_REQUEST.getCode(), String.format("请求参数类型错误:%s", ex.getMessage()));
    }
    
    /**
     * 处理 SpringMVC 参数校验不正确.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<?> methodArgumentNotValidExceptionExceptionHandler(MethodArgumentNotValidException ex) {
        log.warn("[methodArgumentNotValidExceptionExceptionHandler]", ex);
        FieldError fieldError = ex.getBindingResult().getFieldError();
        // 断言，避免告警
        assert fieldError != null;
        return Result.error(GlobalErrorCode.BAD_REQUEST.getCode(),
                String.format("请求参数不正确:%s", fieldError.getDefaultMessage()));
    }
    
    /**
     * 处理 SpringMVC 请求地址不存在.
     * </p>
     * 注意，它需要设置如下两个配置项：
     * </p>
     * 1. spring.mvc.throw-exception-if-no-handler-found 为 true
     * </p>
     * 2. spring.mvc.static-path-pattern 为/statics/**
     *
     * @param ex
     * @return com.zsh.cloud.common.core.pojo.Result<?>
     * @author hang
     * @date 2022/03/14 13:45
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public Result<?> noHandlerFoundExceptionHandler(NoHandlerFoundException ex) {
        log.warn("[noHandlerFoundExceptionHandler]", ex);
        return Result.error(GlobalErrorCode.NOT_FOUND.getCode(), String.format("请求地址不存在:%s", ex.getRequestURL()));
    }
    
    /**
     * 处理 SpringMVC 请求方法不正确.
     * </p>
     * 例如说，A 接口的方法为 GET 方式，结果请求方法为 POST 方式，导致不匹配
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result<?> httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException ex) {
        log.warn("[httpRequestMethodNotSupportedExceptionHandler]", ex);
        return Result.error(GlobalErrorCode.METHOD_NOT_ALLOWED.getCode(), String.format("请求方法不正确:%s", ex.getMessage()));
    }
    
    /**
     * 处理 SpringMVC 参数绑定不正确，本质上也是通过 Validator 校验.
     */
    @ExceptionHandler(BindException.class)
    public Result<?> bindExceptionHandler(BindException ex) {
        log.warn("[handleBindException]", ex);
        FieldError fieldError = ex.getFieldError();
        // 断言，避免告警
        assert fieldError != null;
        return Result.error(GlobalErrorCode.BAD_REQUEST.getCode(),
                String.format("请求参数不正确:%s", fieldError.getDefaultMessage()));
    }
    
    /**
     * 处理 Validator 校验不通过产生的异常.
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Result<?> constraintViolationExceptionHandler(ConstraintViolationException ex) {
        log.warn("[constraintViolationExceptionHandler]", ex);
        ConstraintViolation<?> constraintViolation = ex.getConstraintViolations().iterator().next();
        return Result.error(GlobalErrorCode.BAD_REQUEST.getCode(),
                String.format("请求参数不正确:%s", constraintViolation.getMessage()));
    }
    
    /**
     * 非法数据异常.
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public Result<?> illegalArgumentException(IllegalArgumentException ex) {
        log.warn("IllegalArgumentException:", ex);
        return Result.error(GlobalErrorCode.BAD_REQUEST.getCode(), String.format("请求参数不正确:%s", ex.getMessage()));
    }
    
    /**
     * 处理异常 BaseUncheckedException.
     */
    @ExceptionHandler(value = BaseUncheckedException.class)
    public Result<?> baseUncheckedException(BaseUncheckedException ex) {
        log.info("[baseUncheckedException]", ex);
        return Result.error(ex.getCode(), ex.getMessage());
    }
    
    /**
     * 处理业务异常 ServiceException.
     */
    @ExceptionHandler(value = ServiceException.class)
    public Result<?> serviceExceptionHandler(ServiceException ex) {
        log.info("[serviceExceptionHandler]", ex);
        return Result.error(ex.getCode(), ex.getMessage());
    }
    
    /**
     * 处理系统异常，兜底处理所有的一切.
     *
     * @param e
     * @return com.zsh.cloud.common.core.pojo.Result<?>
     * @author hang
     * @date 2022/03/14 13:46
     */
    @ExceptionHandler(value = Exception.class)
    public Result<?> handleException(Exception e) {
        log.error(e.getMessage(), e);
        return Result.error(GlobalErrorCode.INTERNAL_SERVER_ERROR.getCode(),
                GlobalErrorCode.INTERNAL_SERVER_ERROR.getMsg());
    }
}
