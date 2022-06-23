package com.zsh.cloud.system.domain.model.resource;

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
}
