package com.zsh.cloud.system.domain.service.strategy.impl;

import com.zsh.cloud.common.core.exception.ServiceException;
import com.zsh.cloud.common.core.exception.code.enums.GlobalErrorCode;
import com.zsh.cloud.system.domain.model.org.Org;
import com.zsh.cloud.system.domain.model.org.OrgId;
import com.zsh.cloud.system.domain.model.org.OrgRepository;
import com.zsh.cloud.system.domain.service.strategy.AbstractDataScopeHandler;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysRoleOrgDO;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysRoleOrgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 自定义数据权限.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/8 17:45
 */
@Component("CUSTOMIZE")
public class CustomizeDataScope implements AbstractDataScopeHandler {
    
    @Autowired
    private OrgRepository orgRepository;
    
    @Autowired
    private SysRoleOrgMapper sysRoleOrgMapper;
    
    @Override
    public Set<OrgId> getOrgIds(List<OrgId> orgIdList, String userId) {
        if (orgIdList == null || orgIdList.isEmpty()) {
            throw new ServiceException(GlobalErrorCode.BAD_REQUEST.getCode(), "自定义数据权限类型时，组织不能为空");
        }
        Set<OrgId> orgIds = new HashSet<>(orgIdList);
        for (OrgId orgId : orgIdList) {
            List<Org> children = orgRepository.findChildren(orgId);
            if (!CollectionUtils.isEmpty(children)) {
                orgIds.addAll(children.stream().map(Org::getOrgId).collect(Collectors.toList()));
            }
        }
        return orgIds;
    }
    
    @Override
    public Set<String> getOrgIds(String roleId, String userId) {
        List<SysRoleOrgDO> roleOrgList = sysRoleOrgMapper.selectList(SysRoleOrgDO::getRoleId, roleId);
        return roleOrgList.stream().map(SysRoleOrgDO::getOrgId).collect(Collectors.toSet());
    }
}
