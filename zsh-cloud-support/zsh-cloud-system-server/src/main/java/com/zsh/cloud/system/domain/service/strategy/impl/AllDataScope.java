package com.zsh.cloud.system.domain.service.strategy.impl;

import com.zsh.cloud.system.domain.model.org.OrgId;
import com.zsh.cloud.system.domain.service.strategy.AbstractDataScopeHandler;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysOrgDO;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysOrgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 数据权限：所有数据.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/8 17:45
 */
@Component("ALL")
public class AllDataScope implements AbstractDataScopeHandler {
    
    @Autowired
    private SysOrgMapper sysOrgMapper;
    
    @Override
    public Set<OrgId> getOrgIds(List<OrgId> orgIdList, String userId) {
        List<SysOrgDO> list = sysOrgMapper.selectList();
        return list.stream().map(org -> new OrgId(org.getId())).collect(Collectors.toSet());
    }
    
    @Override
    public Set<String> getOrgIds(String roleId, String userId) {
        return new HashSet<>();
    }
    
}
