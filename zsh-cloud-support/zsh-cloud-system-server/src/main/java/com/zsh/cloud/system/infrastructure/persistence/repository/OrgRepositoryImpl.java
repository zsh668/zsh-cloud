package com.zsh.cloud.system.infrastructure.persistence.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.cloud.system.domain.model.org.Org;
import com.zsh.cloud.system.domain.model.org.OrgId;
import com.zsh.cloud.system.domain.model.org.OrgRepository;
import com.zsh.cloud.system.infrastructure.persistence.converter.OrgConverter;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysOrgDO;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysOrgMapper;
import org.springframework.stereotype.Repository;

/**
 * 组织-Repository实现类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/20 13:44
 */
@Repository
public class OrgRepositoryImpl extends ServiceImpl<SysOrgMapper, SysOrgDO>
        implements OrgRepository, IService<SysOrgDO> {
    
    @Override
    public Org find(OrgId orgId) {
        SysOrgDO sysOrgDO = baseMapper.selectById(orgId.getId());
        return OrgConverter.toOrg(sysOrgDO);
    }
}
