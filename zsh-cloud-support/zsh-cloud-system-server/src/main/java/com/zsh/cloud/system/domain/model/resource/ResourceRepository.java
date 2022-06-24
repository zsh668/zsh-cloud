package com.zsh.cloud.system.domain.model.resource;

import com.zsh.cloud.system.domain.model.menu.MenuId;

import java.util.List;

/**
 * 资源-Repository接口.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/20 14:17
 */
public interface ResourceRepository {
    
    /**
     * 获取资源.
     *
     * @param resourceId
     * @return
     */
    Resource find(ResourceId resourceId);
    
    /**
     * 获取资源.
     *
     * @param resourceCode
     * @return
     */
    List<Resource> find(ResourceCode resourceCode);
    
    /**
     * 获取资源.
     *
     * @param menuId
     * @return
     */
    List<Resource> queryList(MenuId menuId);
    
    /**
     * 保存.
     *
     * @param resource
     */
    ResourceId store(Resource resource);
    
    void remove(List<ResourceId> resourceIds);
}
