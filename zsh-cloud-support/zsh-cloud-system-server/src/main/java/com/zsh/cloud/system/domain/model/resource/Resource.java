package com.zsh.cloud.system.domain.model.resource;

import com.zsh.cloud.common.core.domain.Entity;
import com.zsh.cloud.common.core.enums.StatusEnum;
import lombok.Getter;

/**
 * 资源.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/17 16:46
 */
@Getter
public class Resource implements Entity<Resource> {
    
    /**
     * 资源id.
     */
    private ResourceId resourceId;
    
    /**
     * 资源名称.
     */
    private ResourceName resourceName;
    
    /**
     * 状态.
     */
    private StatusEnum status;
    
    /**
     * 是否有效.
     *
     * @return
     */
    public boolean isEnable() {
        return status == StatusEnum.ENABLE;
    }
    
    /**
     * 启用、禁用.
     */
    public void disable() {
        this.status = isEnable() ? StatusEnum.DISABLE : StatusEnum.ENABLE;
    }
    
    @Override
    public boolean sameIdentityAs(Resource other) {
        return other != null && resourceId.sameValueAs(other.resourceId);
    }
}
