package com.zsh.cloud.system.domain.model.station;

import com.zsh.cloud.common.core.domain.ValueObject;
import com.zsh.cloud.common.core.exception.code.enums.GlobalErrorCode;
import com.zsh.cloud.common.core.util.ServiceAssert;
import org.apache.commons.lang3.StringUtils;

/**
 * 岗位名称.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/25 11:53
 */
public class StationName implements ValueObject<StationName> {
    
    /**
     * 岗位名称.
     */
    private final String name;
    
    public StationName(final String name) {
        ServiceAssert.isTrue(StringUtils.isNotBlank(name), GlobalErrorCode.BAD_REQUEST.getCode(), "岗位名称不能为空");
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    public boolean sameValueAs(StationName other) {
        return other != null && this.name.equals(other.name);
    }
    
    @Override
    public String toString() {
        return name;
    }
}
