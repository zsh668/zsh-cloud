package com.zsh.cloud.system.domain.model.station;

import com.zsh.cloud.common.core.domain.ValueObject;
import com.zsh.cloud.common.core.exception.code.enums.GlobalErrorCode;
import com.zsh.cloud.common.core.util.ServiceAssert;
import org.apache.commons.lang3.StringUtils;

/**
 * 岗位ID.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/25 11:53
 */
public class StationId implements ValueObject<StationId> {
    
    /**
     * 岗位ID.
     */
    private final String id;
    
    public StationId(final String id) {
        ServiceAssert.isTrue(StringUtils.isNotBlank(id), GlobalErrorCode.BAD_REQUEST.getCode(), "岗位id不能为空");
        this.id = id;
    }
    
    public String getId() {
        return id;
    }
    
    @Override
    public boolean sameValueAs(StationId other) {
        return other != null && this.id.equals(other.id);
    }
    
    @Override
    public String toString() {
        return id;
    }
}
