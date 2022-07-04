package com.zsh.cloud.system.application.impl;

import com.zsh.cloud.common.core.util.ListUtils;
import com.zsh.cloud.system.application.OrgQueryService;
import com.zsh.cloud.system.application.assembler.OrgDtoAssembler;
import com.zsh.cloud.system.application.dto.OrgDTO;
import com.zsh.cloud.system.application.dto.OrgTreeDTO;
import com.zsh.cloud.system.application.query.OrgPageQuery;
import com.zsh.cloud.system.domain.model.org.Org;
import com.zsh.cloud.system.domain.model.org.OrgId;
import com.zsh.cloud.system.domain.model.org.OrgRepository;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysOrgDO;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysOrgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
