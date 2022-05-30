package com.zsh.cloud.system.domain.specification;

import com.zsh.cloud.common.core.domain.specification.AbstractSpecification;
import com.zsh.cloud.common.core.exception.code.enums.ServiceErrorCode;
import com.zsh.cloud.common.core.util.Assert;
import com.zsh.cloud.system.domain.model.tenant.Tenant;
import com.zsh.cloud.system.domain.model.tenant.TenantRepository;

/**
 * 租户修改Specification.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/30 16:46
 */
public class TenantUpdateSpecification extends AbstractSpecification<Tenant> {
    
    private TenantRepository tenantRepository;
    
    public TenantUpdateSpecification(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }
    
    @Override
    public boolean isSatisfiedBy(Tenant tenant) {
        Tenant tenant1 = tenantRepository.find(tenant.getTenantName());
        Assert.notTrue(tenant1 != null && !tenant.getTenantId().sameValueAs(tenant1.getTenantId()),
                ServiceErrorCode.TENANT_NAME_EXISTS);
        Tenant tenant2 = tenantRepository.find(tenant.getTenantCode());
        Assert.notTrue(tenant2 != null && !tenant.getTenantId().sameValueAs(tenant2.getTenantId()),
                ServiceErrorCode.TENANT_CODE_EXISTS);
        return true;
    }
}
