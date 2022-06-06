package com.zsh.cloud.common.core.exception.code.enums;

import com.zsh.cloud.common.core.domain.IDict;
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
public enum ServiceErrorCode implements BaseErrorCode, IDict<Integer> {
    
    // 模块 system 【000】 租户 【000】 错误码 【000~999】
    TENANT_NOT_EXISTS(1000000000, "租户不存在"),
    TENANT_NAME_EXISTS(1000000001, "租户名称已存在"),
    TENANT_CODE_EXISTS(1000000002, "租户编码已存在"),
    TENANT_CREATOR_CHANGE(1000000003, "关联租户创建者无法删除或禁用"),
    TENANT_PLATFORM_DELETE(1000000004, "平台租户无法删除"),
    
    // 模块 system 【000】 用户 【001】 错误码 【000~999】
    USER_NOT_EXISTS(1000001000, "用户不存在"),
    USER_PASSWORD_ERROR(1000001001, "密码不正确"),
    USER_PASSWORD_EXPIRATION(1000001002, "密码已过期，请修改密码"),
    USER_ACCOUNT_EXISTS(1000001003, "账号已存在"),
    USER_VERIFICATION_ERROR(1000001004, "验证不通过"),
    USER_NOT_ENABLE(1000001005, "用户被禁用，请联系管理员"),
    
    // 模块 system 【000】 角色 【002】 错误码 【000~999】
    ROLE_NOT_EXISTS(1000002000, "角色不存在"),
    ROLE_NAME_EXISTS(1000002001, "角色名称已存在"),
    ROLE_CODE_EXISTS(1000002002, "角色编码已存在"),
    ROLE_VERIFICATION_ERROR(1000002004, "验证不通过"),
    ROLE_PLATFORM_DELETE(1000002005, "内置角色无法删除"),
    ;
    
    private Integer code;
    
    private String msg;
    
    ServiceErrorCode(Integer code, String msg) {
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
