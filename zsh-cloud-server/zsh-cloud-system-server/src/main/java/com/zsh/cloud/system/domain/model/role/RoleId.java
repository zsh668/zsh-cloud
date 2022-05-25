package com.zsh.cloud.system.domain.model.role;

import com.zsh.cloud.common.core.domain.ValueObject;
import com.zsh.cloud.common.core.exception.code.enums.GlobalErrorCode;
import com.zsh.cloud.common.core.util.ServiceAssert;
import org.apache.commons.lang3.StringUtils;

/**
 * 角色ID.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/25 11:53
 */
public class RoleId implements ValueObject<RoleId> {
    
    /**
     * 角色ID.
     */
    private final String id;
    
    public RoleId(final String id) {
        ServiceAssert.isTrue(StringUtils.isNotBlank(id), GlobalErrorCode.BAD_REQUEST.getCode(), "角色id不能为空");
        this.id = id;
    }
    
    public String getId() {
        return id;
    }
    
    @Override
    public boolean sameValueAs(RoleId other) {
        return other != null && this.id.equals(other.id);
    }
    
    @Override
    public String toString() {
        return id;
    }
}
