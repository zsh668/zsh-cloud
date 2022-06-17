package com.zsh.cloud.system.application.impl;

import com.zsh.cloud.system.application.OrgApplicationService;
import com.zsh.cloud.system.application.assembler.OrgDtoAssembler;
import com.zsh.cloud.system.application.command.OrgCreateCommand;
import com.zsh.cloud.system.application.command.OrgUpdateCommand;
import com.zsh.cloud.system.domain.model.org.Org;
import com.zsh.cloud.system.domain.model.org.OrgId;
import com.zsh.cloud.system.domain.model.org.OrgRepository;
import com.zsh.cloud.system.domain.specification.OrgCreateSpecification;
import com.zsh.cloud.system.domain.specification.OrgDeleteSpecification;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysOrgMapper;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 组织应用服务实现类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/14 14:24
 */
@Service
public class OrgApplicationServiceImpl implements OrgApplicationService {
    
    @Autowired
    private OrgRepository orgRepository;
    
    @Autowired
    private SysOrgMapper sysOrgMapper;
    
    @Autowired
    private SysUserMapper sysUserMapper;
    
    @Autowired
    private OrgDtoAssembler orgDtoAssembler;
    
    @Override
    public void save(OrgCreateCommand orgCommand) {
        Org org = orgDtoAssembler.toOrg(orgCommand);
        OrgCreateSpecification specification = new OrgCreateSpecification(orgRepository);
        specification.isSatisfiedBy(org);
        orgRepository.store(org);
    }
    
    @Override
    public void update(OrgUpdateCommand orgCommand) {
        Org org = orgDtoAssembler.toOrg(orgCommand);
        OrgCreateSpecification specification = new OrgCreateSpecification(orgRepository);
        specification.isSatisfiedBy(org);
        orgRepository.store(org);
    }
    
    @Override
    public void deleteBatch(List<String> ids) {
        List<OrgId> orgIds = new ArrayList<>();
        OrgDeleteSpecification specification = new OrgDeleteSpecification(sysOrgMapper, sysUserMapper);
        ids.forEach(id -> {
            OrgId orgId = new OrgId(id);
            Org org = orgRepository.find(orgId);
            specification.isSatisfiedBy(org);
            orgIds.add(orgId);
        });
        orgRepository.remove(orgIds);
    }
    
    @Override
    public void disable(String id) {
        Org org = orgRepository.find(new OrgId(id));
        org.disable();
        orgRepository.store(org);
    }
}
