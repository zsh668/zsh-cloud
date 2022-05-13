package com.zsh.cloud.common.core.exception;

import com.zsh.cloud.common.core.exception.code.BaseErrorCode;

/**
 * 业务逻辑异常.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/3/24 17:29
 */
public class ServiceException extends BaseUncheckedException {
    
    public ServiceException(BaseErrorCode errorCode) {
        super(errorCode);
    }
    
    public ServiceException(Integer code, String message) {
        super(code, message);
    }
    
    public ServiceException(Integer code, String format, Object... args) {
        super(code, format, args);
    }
}
