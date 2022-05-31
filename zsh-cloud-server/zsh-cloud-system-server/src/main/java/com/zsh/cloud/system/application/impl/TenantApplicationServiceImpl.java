package com.zsh.cloud.system.application.impl;

import com.zsh.cloud.common.core.exception.code.enums.ServiceErrorCode;
import com.zsh.cloud.common.core.util.Assert;
import com.zsh.cloud.system.application.TenantApplicationService;
import com.zsh.cloud.system.application.assembler.TenantDtoAssembler;
import com.zsh.cloud.system.application.command.TenantCreateCommand;
import com.zsh.cloud.system.application.command.TenantUpdateCommand;
import com.zsh.cloud.system.domain.model.tenant.Tenant;
import com.zsh.cloud.system.domain.model.tenant.TenantId;
import com.zsh.cloud.system.domain.model.tenant.TenantRepository;
import com.zsh.cloud.system.domain.specification.TenantCreateSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 租户应用服务实现类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/26 11:22
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TenantApplicationServiceImpl implements TenantApplicationService {
    
    @Autowired
    private TenantRepository tenantRepository;
    
    @Autowired
    private TenantDtoAssembler tenantDtoAssembler;
    
    @Override
    public void save(TenantCreateCommand tenantCommand) {
        Tenant tenant = tenantDtoAssembler.toTenant(tenantCommand);
        TenantCreateSpecification specification = new TenantCreateSpecification(tenantRepository);
        specification.isSatisfiedBy(tenant);
        tenantRepository.store(tenant);
    }
    
    @Override
    public void update(TenantUpdateCommand tenantCommand) {
        Tenant tenant = tenantDtoAssembler.toTenant(tenantCommand);
        TenantCreateSpecification specification = new TenantCreateSpecification(tenantRepository);
        specification.isSatisfiedBy(tenant);
        tenantRepository.store(tenant);
    }
    
    @Override
    public void deleteBatch(List<String> ids) {
        List<TenantId> tenantIds = new ArrayList<>();
        ids.forEach(id -> tenantIds.add(new TenantId(id)));
        for (TenantId tenantId : tenantIds) {
            Assert.notTrue(tenantId.isPlatformId(), ServiceErrorCode.TENANT_PLATFORM_DELETE);
        }
        tenantRepository.remove(tenantIds);
    }
    
    @Override
    public void disable(String id) {
        TenantId tenantId = new TenantId(id);
        Assert.notTrue(tenantId.isPlatformId(), ServiceErrorCode.TENANT_PLATFORM_DELETE);
        Tenant tenant = tenantRepository.find(tenantId);
        tenant.disable();
        tenantRepository.store(tenant);
    }
}
