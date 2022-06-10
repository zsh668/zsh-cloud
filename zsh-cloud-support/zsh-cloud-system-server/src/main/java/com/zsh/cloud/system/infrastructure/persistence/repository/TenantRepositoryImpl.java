package com.zsh.cloud.system.infrastructure.persistence.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.cloud.system.domain.model.tenant.Tenant;
import com.zsh.cloud.system.domain.model.tenant.TenantCode;
import com.zsh.cloud.system.domain.model.tenant.TenantId;
import com.zsh.cloud.system.domain.model.tenant.TenantName;
import com.zsh.cloud.system.domain.model.tenant.TenantRepository;
import com.zsh.cloud.system.infrastructure.persistence.converter.TenantConverter;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysTenantDO;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysTenantMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 租户-Repository实现类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/20 13:44
 */
@Repository
public class TenantRepositoryImpl extends ServiceImpl<SysTenantMapper, SysTenantDO>
        implements TenantRepository, IService<SysTenantDO> {
    
    @Override
    public Tenant find(TenantId tenantId) {
        SysTenantDO sysTenantDO = baseMapper.selectById(tenantId.getId());
        if (sysTenantDO == null) {
            return null;
        }
        return TenantConverter.toTenant(sysTenantDO);
    }
    
    @Override
    public Tenant find(TenantName tenantName) {
        SysTenantDO sysTenantDO = baseMapper.selectOne(SysTenantDO::getTenantName, tenantName.getName());
        if (sysTenantDO == null) {
            return null;
        }
        return TenantConverter.toTenant(sysTenantDO);
    }
    
    @Override
    public Tenant find(TenantCode tenantCode) {
        SysTenantDO sysTenantDO = baseMapper.selectOne(SysTenantDO::getTenantCode, tenantCode.getCode());
        if (sysTenantDO == null) {
            return null;
        }
        return TenantConverter.toTenant(sysTenantDO);
    }
    
    @Override
    public TenantId store(Tenant tenant) {
        SysTenantDO sysTenantDO = TenantConverter.fromTenant(tenant);
        this.saveOrUpdate(sysTenantDO);
        return new TenantId(sysTenantDO.getId());
    }
    
    @Override
    public void remove(List<TenantId> tenantIds) {
        List<String> ids = new ArrayList<>();
        tenantIds.forEach(tenantId -> {
            ids.add(tenantId.getId());
        });
        this.removeByIds(ids);
    }
}
