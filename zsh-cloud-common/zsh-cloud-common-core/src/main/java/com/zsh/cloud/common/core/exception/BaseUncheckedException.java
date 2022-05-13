package com.zsh.cloud.common.core.exception;

import com.zsh.cloud.common.core.exception.code.BaseErrorCode;

/**
 * 非运行期异常基类，所有自定义非运行时异常继承该类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/22 11:51
 */
public class BaseUncheckedException extends RuntimeException implements BaseException {
    
    /**
     * 异常信息
     */
    protected String message;
    
    /**
     * 具体异常码
     */
    protected Integer code;
    
    public BaseUncheckedException(BaseErrorCode errorCode) {
        super(errorCode.getMsg());
        this.code = errorCode.getCode();
        this.message = errorCode.getMsg();
    }
    
    public BaseUncheckedException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
    
    public BaseUncheckedException(int code, String format, Object... args) {
        super(String.format(format, args));
        this.code = code;
        this.message = String.format(format, args);
    }
    
    @Override
    public String getMessage() {
        return message;
    }
    
    @Override
    public Integer getCode() {
        return code;
    }
}
