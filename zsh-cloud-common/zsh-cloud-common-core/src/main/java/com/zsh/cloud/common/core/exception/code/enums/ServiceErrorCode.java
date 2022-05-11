package com.zsh.cloud.common.core.exception.code.enums;

import com.zsh.cloud.common.core.exception.code.BaseErrorCode;

/**
 * 业务错误码枚举.
 * </p>
 * 一共 10 位，分成四段
 * </p>
 * 第一段，1 位，类型 1 - 业务级别异常 x - 预留
 * </p>
 * 第二段，3 位，系统类型 001 - 用户系统 002 - 商品系统 003 - 订单系统 004 - 支付系统 005 - 优惠劵系统 ... - ...
 * </p>
 * 第三段，3 位，模块 不限制规则。 一般建议，每个系统里面，可能有多个模块，可以再去做分段。以用户系统为例子： 001 - OAuth2 模块 002 - User 模块 003 - MobileCode 模块
 * </p>
 * 第四段，3 位，错误码 不限制规则。 一般建议，每个模块自增。
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/3/24 17:30
 */
public enum ServiceErrorCode implements BaseErrorCode {
    // 模块 system 【000】 用户 【000】错误码 【000~999】
    USER_NOT_EXISTS(1000000000, "用户不存在"),
    USER_PASSWORD_ERROR(1000000001, "密码不正确"),
    ;
    
    private int code;
    
    private String msg;
    
    ServiceErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    
    @Override
    public int getCode() {
        return code;
    }
    
    @Override
    public String getMsg() {
        return msg;
    }
}
