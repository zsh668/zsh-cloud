package com.zsh.cloud.system.domain.service.strategy.impl;

import com.zsh.cloud.system.domain.model.org.Org;
import com.zsh.cloud.system.domain.model.org.OrgId;
import com.zsh.cloud.system.domain.model.org.OrgRepository;
import com.zsh.cloud.system.domain.service.strategy.AbstractDataScopeHandler;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysUserDO;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 本级以及子级.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/8 17:45
 */
@Component("THIS_LEVEL_CHILDREN")
public class ThisLevelChildrenDataScope implements AbstractDataScopeHandler {
    
    @Autowired
    private SysUserMapper sysUserMapper;
    
    @Autowired
    private OrgRepository orgRepository;
    
    @Override
    public Set<OrgId> getOrgIds(List<OrgId> orgIdList, String userId) {
        SysUserDO user = sysUserMapper.selectById(userId);
        if (user == null) {
            return Collections.emptySet();
        }
        List<Org> children = orgRepository.findChildren(new OrgId(user.getOrgId()));
        return children.stream().map(Org::getOrgId).collect(Collectors.toSet());
    }
    
    @Override
    public Set<String> getOrgIds(String roleId, String userId) {
        Set<String> orgIds = new HashSet<>();
        SysUserDO user = sysUserMapper.selectById(userId);
        if (user != null) {
            List<Org> orgList = orgRepository.findChildren(new OrgId(user.getOrgId()));
            orgIds = orgList.stream().map(org -> org.getOrgId().getId()).collect(Collectors.toSet());
        }
        return orgIds;
    }
}
