package com.zsh.cloud.system.domain.model.resource;

import com.zsh.cloud.common.core.domain.Entity;
import com.zsh.cloud.common.core.enums.StatusEnum;
import com.zsh.cloud.system.domain.model.menu.MenuId;
import lombok.Getter;
import org.springframework.http.HttpMethod;

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
     * 资源编码.
     */
    private ResourceCode resourceCode;
    
    /**
     * 资源名称.
     */
    private ResourceName resourceName;
    
    /**
     * 菜单ID.
     */
    private MenuId menuId;
    
    /**
     * 请求方式.
     */
    private HttpMethod method;
    
    /**
     * 资源地址.
     */
    private ResourceUrl resourceUrl;
    
    /**
     * 状态.
     */
    private StatusEnum status;
    
    /**
     * 功能描述.
     */
    private String describe;
    
    public Resource(ResourceCode resourceCode, ResourceName resourceName, MenuId menuId, HttpMethod method,
            ResourceUrl resourceUrl, String describe) {
        this.resourceCode = resourceCode;
        this.resourceName = resourceName;
        this.menuId = menuId;
        this.method = method;
        this.resourceUrl = resourceUrl;
        this.status = StatusEnum.ENABLE;
        this.describe = describe;
    }
    
    public Resource(ResourceId resourceId, ResourceCode resourceCode, ResourceName resourceName, MenuId menuId,
            HttpMethod method, ResourceUrl resourceUrl, StatusEnum status, String describe) {
        this.resourceId = resourceId;
        this.resourceCode = resourceCode;
        this.resourceName = resourceName;
        this.menuId = menuId;
        this.method = method;
        this.resourceUrl = resourceUrl;
        this.status = status;
        this.describe = describe;
    }
    
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
