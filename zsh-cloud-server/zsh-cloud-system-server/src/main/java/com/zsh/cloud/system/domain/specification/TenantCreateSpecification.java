package com.zsh.cloud.system.domain.specification;

import com.zsh.cloud.common.core.domain.specification.AbstractSpecification;
import com.zsh.cloud.common.core.exception.code.enums.ServiceErrorCode;
import com.zsh.cloud.common.core.util.Assert;
import com.zsh.cloud.system.domain.model.tenant.Tenant;
import com.zsh.cloud.system.domain.model.tenant.TenantRepository;

/**
 * 租户创建Specification.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/30 16:46
 */
public class TenantCreateSpecification extends AbstractSpecification<Tenant> {
    
    private TenantRepository tenantRepository;
    
    public TenantCreateSpecification(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }
    
    @Override
    public boolean isSatisfiedBy(Tenant tenant) {
        if (tenant.getTenantName() != null) {
            Tenant existTenant = tenantRepository.find(tenant.getTenantName());
            Assert.notTrue(existTenant != null && !existTenant.getTenantId().sameValueAs(tenant.getTenantId()),
                    ServiceErrorCode.TENANT_NAME_EXISTS);
        }
        if (tenant.getTenantCode() != null) {
            Tenant existTenant = tenantRepository.find(tenant.getTenantCode());
            Assert.notTrue(existTenant != null && !existTenant.getTenantId().sameValueAs(tenant.getTenantId()),
                    ServiceErrorCode.TENANT_NAME_EXISTS);
        }
        return true;
    }
}
