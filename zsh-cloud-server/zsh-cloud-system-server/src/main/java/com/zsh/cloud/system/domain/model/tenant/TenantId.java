package com.zsh.cloud.system.domain.model.tenant;

import com.zsh.cloud.common.core.domain.ValueObject;
import com.zsh.cloud.common.core.exception.code.enums.GlobalErrorCode;
import com.zsh.cloud.common.core.util.ServiceAssert;
import org.apache.commons.lang3.StringUtils;

/**
 * 租户ID.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/25 11:53
 */
public class TenantId implements ValueObject<TenantId> {
    
    /**
     * 平台租户
     */
    public static final String PLATFORM_TENANT = "1";
    
    /**
     * 租户ID
     */
    private final String id;
    
    public TenantId(final String id) {
        ServiceAssert.isTrue(StringUtils.isNotBlank(id), GlobalErrorCode.BAD_REQUEST.getCode(), "租户id不能为空");
        this.id = id;
    }
    
    /**
     * 是否是平台租户.
     *
     * @return
     */
    public boolean isPlatformId() {
        return PLATFORM_TENANT.equals(id);
    }
    
    public String getId() {
        return id;
    }
    
    @Override
    public boolean sameValueAs(TenantId other) {
        return other != null && this.id.equals(other.id);
    }
    
    @Override
    public String toString() {
        return id;
    }
}
