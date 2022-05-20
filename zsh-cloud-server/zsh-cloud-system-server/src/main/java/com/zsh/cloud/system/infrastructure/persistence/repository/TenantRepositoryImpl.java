package com.zsh.cloud.system.infrastructure.persistence.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.cloud.system.domain.model.tenant.TenantRepository;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysTenantDO;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysTenantMapper;
import org.springframework.stereotype.Repository;

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
    
}
