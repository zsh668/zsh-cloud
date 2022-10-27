package com.zsh.cloud.common.core.exception.code.enums;

import com.zsh.cloud.common.core.domain.IDict;
import com.zsh.cloud.common.core.exception.code.BaseErrorCode;

/**
 * 全局错误码枚举.
 *
 * @author hang
 * @version 1.0
 * @date 2022/03/14 10:28
 */
public enum GlobalErrorCode implements BaseErrorCode, IDict<Integer> {
    
    SUCCESS(0, "成功"),
    
    BAD_REQUEST(400, "请求参数不正确"),
    
    UNAUTHORIZED(401, "权限不足"),
    
    AUTHENTICATION_FAILED(402, "认证失败"),
    
    NOT_FOUND(404, "请求未找到"),
    
    METHOD_NOT_ALLOWED(405, "请求方法不正确"),
    
    REPETITIVE_OPERATION(408, "重复操作"),
    
    INTERNAL_SERVER_ERROR(500, "服务繁忙"),
    
    GATEWAY_TIMEOUT(504, "请求超时，请重试"),
    
    TOKEN_INVALID(524, "token无效，请重新登录"),
    
    TOKEN_EXPIRE(525, "token已过期，请重新登录"),
    
    TOKEN_REFRESH_EXPIRE(526, "refresh_token已过期,请重新登录"),
    
    TOKEN_REFRESH_FAIL(527, "刷新token失败"),
    
    MISS_HEADER(529, "请求头缺失");
    
    private Integer code;
    
    private String msg;
    
    GlobalErrorCode(int code, String msg) {
        init(code, msg);
        this.code = code;
        this.msg = msg;
    }
    
    @Override
    public Integer getCode() {
        return code;
    }
    
    @Override
    public String getMsg() {
        return msg;
    }
}
