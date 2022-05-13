package com.zsh.cloud.common.core.exception;

/**
 * 异常接口类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/22 11:50
 */
public interface BaseException {
    
    /**
     * 返回异常信息
     *
     * @return
     */
    String getMessage();
    
    /**
     * 返回异常编码
     *
     * @return
     */
    Integer getCode();
}
