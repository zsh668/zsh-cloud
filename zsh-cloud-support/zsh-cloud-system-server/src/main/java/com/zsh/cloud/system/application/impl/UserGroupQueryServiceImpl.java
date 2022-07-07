package com.zsh.cloud.system.application.impl;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.system.application.UserGroupQueryService;
import com.zsh.cloud.system.application.assembler.UserGroupDtoAssembler;
import com.zsh.cloud.system.application.model.dto.UserGroupDTO;
import com.zsh.cloud.system.application.model.dto.UserGroupPageDTO;
import com.zsh.cloud.system.application.model.query.UserGroupPageQuery;
import com.zsh.cloud.system.domain.model.usergroup.UserGroup;
import com.zsh.cloud.system.domain.model.usergroup.UserGroupId;
import com.zsh.cloud.system.domain.model.usergroup.UserGroupRepository;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysUserGroupDO;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysUserGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户组查询服务实现类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/8 09:50
 */
@Service
public class UserGroupQueryServiceImpl implements UserGroupQueryService {
    
    @Autowired
    private SysUserGroupMapper sysUserGroupMapper;
    
    @Autowired
    private UserGroupRepository userGroupRepository;
    
    @Autowired
    private UserGroupDtoAssembler userGroupDtoAssembler;
    
    @Override
    public Page<UserGroupPageDTO> queryPage(UserGroupPageQuery query) {
        Page<SysUserGroupDO> page = sysUserGroupMapper.selectPage(query);
        return userGroupDtoAssembler.toDto(page);
    }
    
    @Override
    public UserGroupDTO find(String userGroupId) {
        UserGroup userGroup = userGroupRepository.find(new UserGroupId(userGroupId));
        return userGroupDtoAssembler.fromUser(userGroup);
    }
}
