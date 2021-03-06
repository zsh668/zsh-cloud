package com.zsh.cloud.system.application.impl;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.system.application.TenantQueryService;
import com.zsh.cloud.system.application.assembler.TenantDtoAssembler;
import com.zsh.cloud.system.application.model.dto.TenantDTO;
import com.zsh.cloud.system.application.model.dto.TenantPageDTO;
import com.zsh.cloud.system.application.model.query.TenantPageQuery;
import com.zsh.cloud.system.domain.model.tenant.Tenant;
import com.zsh.cloud.system.domain.model.tenant.TenantId;
import com.zsh.cloud.system.domain.model.tenant.TenantRepository;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysTenantDO;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysTenantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 租户查询服务实现类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/26 11:09
 */
@Service
public class TenantQueryServiceImpl implements TenantQueryService {
    
    @Autowired
    private SysTenantMapper sysTenantMapper;
    
    @Autowired
    private TenantDtoAssembler tenantDtoAssembler;
    
    @Autowired
    private TenantRepository tenantRepository;
    
    @Override
    public Page<TenantPageDTO> queryPage(TenantPageQuery query) {
        Page<SysTenantDO> page = sysTenantMapper.selectPage(query);
        return tenantDtoAssembler.toDto(page);
    }
    
    @Override
    public List<TenantPageDTO> queryList(TenantPageQuery query) {
        List<SysTenantDO> page = sysTenantMapper.selectList(query);
        return tenantDtoAssembler.toDto(page);
    }
    
    @Override
    public TenantDTO find(String tenantId) {
        Tenant tenant = tenantRepository.find(new TenantId(tenantId));
        return tenantDtoAssembler.fromTenant(tenant);
    }
}
