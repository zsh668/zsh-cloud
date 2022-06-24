package com.zsh.cloud.system.infrastructure.persistence.converter;

import com.zsh.cloud.common.core.domain.IDict;
import com.zsh.cloud.common.core.enums.StatusEnum;
import com.zsh.cloud.common.core.exception.code.enums.ServiceErrorCode;
import com.zsh.cloud.common.core.util.Assert;
import com.zsh.cloud.system.domain.model.menu.MenuId;
import com.zsh.cloud.system.domain.model.resource.Resource;
import com.zsh.cloud.system.domain.model.resource.ResourceCode;
import com.zsh.cloud.system.domain.model.resource.ResourceId;
import com.zsh.cloud.system.domain.model.resource.ResourceName;
import com.zsh.cloud.system.domain.model.resource.ResourceUrl;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysResourceDO;
import org.springframework.http.HttpMethod;

/**
 * 资源Converter.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/17 17:00
 */
public class ResourceConverter {
    
    /**
     * 转换.
     *
     * @param sysResourceDO
     * @return
     */
    public static Resource toResource(SysResourceDO sysResourceDO) {
        if (sysResourceDO == null) {
            return null;
        }
        return new Resource(new ResourceId(sysResourceDO.getId()), new ResourceCode(sysResourceDO.getResourceCode()),
                new ResourceName(sysResourceDO.getResourceName()), new MenuId(sysResourceDO.getMenuId()),
                HttpMethod.resolve(sysResourceDO.getMethod()), new ResourceUrl(sysResourceDO.getUrl()),
                IDict.getByCode(StatusEnum.class, sysResourceDO.getStatus()), sysResourceDO.getDescribe());
    }
    
    /**
     * 转换.
     *
     * @param resource
     * @return
     */
    public static SysResourceDO fromResource(Resource resource) {
        Assert.notNull(resource, ServiceErrorCode.RESOURCE_NOT_EXISTS);
        SysResourceDO sysResourceDO = new SysResourceDO();
        sysResourceDO.setId(resource.getResourceId() == null ? null : resource.getResourceId().getId());
        sysResourceDO.setResourceCode(resource.getResourceCode() == null ? null : resource.getResourceCode().getCode());
        sysResourceDO.setResourceName(resource.getResourceName() == null ? null : resource.getResourceName().getName());
        sysResourceDO.setMenuId(resource.getMenuId() == null ? null : resource.getMenuId().getId());
        sysResourceDO.setMethod(resource.getMethod() == null ? null : resource.getMethod().name());
        sysResourceDO.setUrl(resource.getResourceUrl() == null ? null : resource.getResourceUrl().getUrl());
        sysResourceDO.setStatus(resource.getStatus() == null ? null : resource.getStatus().getCode());
        sysResourceDO.setDescribe(resource.getDescribe());
        return sysResourceDO;
    }
}
