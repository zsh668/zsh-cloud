package com.zsh.cloud.system.domain.model.tenant;

import com.zsh.cloud.common.core.domain.ValueObject;
import com.zsh.cloud.common.core.exception.code.enums.GlobalErrorCode;
import com.zsh.cloud.common.core.util.ServiceAssert;
import org.apache.commons.lang3.StringUtils;

/**
 * 租户名称.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/25 11:53
 */
public class TenantName implements ValueObject<TenantName> {
    
    /**
     * 租户名称.
     */
    private final String name;
    
    public TenantName(final String name) {
        ServiceAssert.isTrue(StringUtils.isNotBlank(name), GlobalErrorCode.BAD_REQUEST.getCode(), "租户名称不能为空");
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    public boolean sameValueAs(TenantName other) {
        return other != null && this.name.equals(other.name);
    }
    
    @Override
    public String toString() {
        return name;
    }
}
