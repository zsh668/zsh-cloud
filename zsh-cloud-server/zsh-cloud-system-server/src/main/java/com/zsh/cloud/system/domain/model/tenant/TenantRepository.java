package com.zsh.cloud.system.domain.model.tenant;

import java.util.List;

/**
 * 租户-Repository接口.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/20 13:45
 */
public interface TenantRepository {
    
    /**
     * 通过租户id获取租户.
     *
     * @param tenantId
     * @return
     */
    Tenant find(TenantId tenantId);
    
    /**
     * 通过租户名称获取租户
     *
     * @param tenantName
     * @return
     */
    Tenant find(TenantName tenantName);
    
    /**
     * 通过租户编码获取租户
     *
     * @param tenantCode
     * @return
     */
    Tenant find(TenantCode tenantCode);
    
    /**
     * 保存.
     *
     * @param tenant
     */
    TenantId store(Tenant tenant);
    
    /**
     * 删除.
     *
     * @param tenantIds
     */
    void remove(List<TenantId> tenantIds);
}
