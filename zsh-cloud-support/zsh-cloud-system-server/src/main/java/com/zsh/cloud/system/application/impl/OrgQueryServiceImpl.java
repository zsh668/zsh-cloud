package com.zsh.cloud.system.application.impl;

import com.zsh.cloud.common.core.util.ListUtils;
import com.zsh.cloud.system.application.OrgQueryService;
import com.zsh.cloud.system.application.assembler.OrgDtoAssembler;
import com.zsh.cloud.system.application.model.dto.OrgDTO;
import com.zsh.cloud.system.application.model.dto.OrgTreeDTO;
import com.zsh.cloud.system.application.model.query.OrgPageQuery;
import com.zsh.cloud.system.domain.model.org.Org;
import com.zsh.cloud.system.domain.model.org.OrgId;
import com.zsh.cloud.system.domain.model.org.OrgRepository;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysOrgDO;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysOrgMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 组织查询服务实现类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/14 14:23
 */
@Service
public class OrgQueryServiceImpl implements OrgQueryService {
    
    @Autowired
    private SysOrgMapper sysOrgMapper;
    
    @Autowired
    private OrgRepository orgRepository;
    
    @Autowired
    private OrgDtoAssembler orgDtoAssembler;
    
    @Override
    public List<OrgTreeDTO> queryList(OrgPageQuery orgPageQuery) {
        List<SysOrgDO> orgList = sysOrgMapper.selectList(orgPageQuery);
        List<OrgTreeDTO> orgs = orgDtoAssembler.toDto(orgList);
        buildParentStatus(orgs, null);
        return ListUtils.treeify(orgs);
    }
    
    @Override
    public OrgDTO find(String id) {
        Org org = orgRepository.find(new OrgId(id));
        return orgDtoAssembler.fromOrg(org);
    }
    
    @Override
    public List<OrgDTO> findChildren(String orgId) {
        List<OrgDTO> orgs = new ArrayList<>();
        if (StringUtils.isBlank(orgId)) {
            return orgs;
        }
        Org org = orgRepository.find(new OrgId(orgId));
        OrgDTO orgDTO = orgDtoAssembler.fromOrg(org);
        if (orgDTO == null) {
            return orgs;
        }
        orgs.add(orgDTO);
        findChildren(orgId, orgs);
        return orgs;
    }
    
    /**
     * 查询组织的全部子节点.
     *
     * @param orgId
     * @param orgs
     */
    private void findChildren(String orgId, List<OrgDTO> orgs) {
        List<Org> children = orgRepository.queryList(new OrgId(orgId));
        if (!CollectionUtils.isEmpty(children)) {
            children.forEach(org -> orgs.add(orgDtoAssembler.fromOrg(org)));
            children.forEach(org -> findChildren(org.getOrgId().getId(), orgs));
        }
    }
    
    /**
     * 批量设置组织树的状态
     *
     * @param list
     * @param status
     */
    private void buildParentStatus(List<OrgTreeDTO> list, Boolean status) {
        for (OrgTreeDTO orgTreeDTO : list) {
            orgTreeDTO.setParentStatus(status);
            if (!CollectionUtils.isEmpty(orgTreeDTO.getChildren())) {
                Boolean parentStatus = orgTreeDTO.getStatus();
                buildParentStatus(orgTreeDTO.getChildren(), parentStatus);
            }
        }
    }
}
