package com.zsh.cloud.system.domain.model.org;

import com.zsh.cloud.common.core.domain.ValueObject;
import com.zsh.cloud.common.core.exception.code.enums.GlobalErrorCode;
import com.zsh.cloud.common.core.util.ServiceAssert;
import org.apache.commons.lang3.StringUtils;

/**
 * 组织名称.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/25 11:53
 */
public class OrgName implements ValueObject<OrgName> {
    
    /**
     * 组织名称.
     */
    private final String name;
    
    public OrgName(final String name) {
        ServiceAssert.isTrue(StringUtils.isNotBlank(name), GlobalErrorCode.BAD_REQUEST.getCode(), "组织名称不能为空");
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    public boolean sameValueAs(OrgName other) {
        return other != null && this.name.equals(other.name);
    }
    
    @Override
    public String toString() {
        return name;
    }
}
