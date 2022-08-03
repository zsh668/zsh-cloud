package com.zsh.cloud.sms.domain.model.signature;

import com.zsh.cloud.common.core.domain.ValueObject;
import com.zsh.cloud.common.core.exception.code.enums.GlobalErrorCode;
import com.zsh.cloud.common.core.util.ServiceAssert;
import org.apache.commons.lang3.StringUtils;

/**
 * 签名名称.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/25 11:53
 */
public class SignatureName implements ValueObject<SignatureName> {
    
    /**
     * 租户管理员角色名称
     */
    public static final String TENANT_ADMIN = "租户管理";
    
    /**
     * 角色名称.
     */
    private final String name;
    
    public SignatureName(final String name) {
        ServiceAssert.isTrue(StringUtils.isNotBlank(name), GlobalErrorCode.BAD_REQUEST.getCode(), "角色名称不能为空");
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    public boolean sameValueAs(SignatureName other) {
        return other != null && this.name.equals(other.name);
    }
    
    @Override
    public String toString() {
        return name;
    }
}
