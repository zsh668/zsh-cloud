package com.zsh.cloud.system.domain.service.strategy.impl;

import com.zsh.cloud.system.domain.model.org.OrgId;
import com.zsh.cloud.system.domain.service.strategy.AbstractDataScopeHandler;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysUserDO;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * 本级数据权限.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/8 17:45
 */
@Component("THIS_LEVEL")
public class ThisLevelDataScope implements AbstractDataScopeHandler {
    
    @Autowired
    private SysUserMapper sysUserMapper;
    
    @Override
    public Set<OrgId> getOrgIds(List<OrgId> orgIdList, String userId) {
        SysUserDO user = sysUserMapper.selectById(userId);
        if (user == null) {
            return Collections.emptySet();
        }
        return Collections.singleton(new OrgId(user.getOrgId()));
    }
    
    @Override
    public Set<String> getOrgIds(String roleId, String userId) {
        SysUserDO user = sysUserMapper.selectById(userId);
        if (user == null) {
            return Collections.emptySet();
        }
        return Collections.singleton(user.getOrgId());
    }
}
