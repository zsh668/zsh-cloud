package com.zsh.cloud.system.application.impl;

import com.zsh.cloud.common.core.enums.StatusEnum;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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
@Transactional(rollbackFor = Exception.class)
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
        // 禁用当前ID
        Org org = orgRepository.find(new OrgId(id));
        // 禁用 父ID为 id的子集
        disableParent(id, org.getStatus());
        org.disable(org.getStatus());
        orgRepository.store(org);
    }
    
    /**
     * 禁用父ID的子集.
     *
     * @param parentId
     * @param status
     */
    private void disableParent(String parentId, StatusEnum status) {
        // 禁用 父ID为 id的子集
        List<Org> orgs = orgRepository.queryList(new OrgId(parentId));
        if (!CollectionUtils.isEmpty(orgs)) {
            orgs.forEach(org -> {
                org.disable(status);
                orgRepository.store(org);
                disableParent(org.getOrgId().getId(), status);
            });
        }
    }
}
