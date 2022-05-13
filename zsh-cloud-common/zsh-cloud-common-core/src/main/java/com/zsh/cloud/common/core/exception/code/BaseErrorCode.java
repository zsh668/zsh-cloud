package com.zsh.cloud.common.core.exception.code;

/**
 * 错误码对象
 *
 * @author hang
 * @version 1.0
 * @date 2022/03/14 10:27
 */
public interface BaseErrorCode {
    
    /**
     * 异常编码
     *
     * @return
     */
    Integer getCode();

    /**
     * 异常消息
     * @return
     */
    String getMsg();

}
