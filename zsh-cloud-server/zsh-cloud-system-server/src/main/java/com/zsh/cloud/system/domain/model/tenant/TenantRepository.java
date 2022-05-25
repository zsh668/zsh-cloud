package com.zsh.cloud.system.domain.model.tenant;

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
}
