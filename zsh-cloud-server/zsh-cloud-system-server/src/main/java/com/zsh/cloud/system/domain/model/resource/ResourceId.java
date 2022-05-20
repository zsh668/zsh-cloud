package com.zsh.cloud.system.domain.model.resource;

import com.zsh.cloud.common.core.domain.ValueObject;
import org.apache.commons.lang3.StringUtils;

/**
 * 资源ID.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/25 11:53
 */
public class ResourceId implements ValueObject<ResourceId> {
    
    /**
     * 资源ID.
     */
    private final String id;
    
    public ResourceId(final String id) {
        if (StringUtils.isEmpty(id)) {
            throw new IllegalArgumentException("资源id不能为空");
        }
        this.id = id;
    }
    
    public String getId() {
        return id;
    }
    
    @Override
    public boolean sameValueAs(ResourceId other) {
        return other != null && this.id.equals(other.id);
    }
    
    @Override
    public String toString() {
        return id;
    }
}
