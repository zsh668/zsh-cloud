package com.zsh.cloud.system.application.impl;

import com.zsh.cloud.system.application.UserGroupApplicationService;
import com.zsh.cloud.system.application.assembler.GroupUserDtoAssembler;
import com.zsh.cloud.system.application.assembler.UserGroupDtoAssembler;
import com.zsh.cloud.system.application.model.command.GroupUserCommand;
import com.zsh.cloud.system.application.model.command.UserGroupCreateCommand;
import com.zsh.cloud.system.application.model.command.UserGroupUpdateCommand;
import com.zsh.cloud.system.domain.model.groupuser.GroupUser;
import com.zsh.cloud.system.domain.model.groupuser.GroupUserRepository;
import com.zsh.cloud.system.domain.model.usergroup.UserGroup;
import com.zsh.cloud.system.domain.model.usergroup.UserGroupId;
import com.zsh.cloud.system.domain.model.usergroup.UserGroupRepository;
import com.zsh.cloud.system.domain.specification.UserGroupCreateSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户组应用服务实现类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/8 09:51
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserGroupApplicationServiceImpl implements UserGroupApplicationService {
    
    @Autowired
    private UserGroupRepository userGroupRepository;
    
    @Autowired
    private UserGroupDtoAssembler userGroupDtoAssembler;
    
    @Autowired
    private GroupUserRepository groupUserRepository;
    
    @Autowired
    private GroupUserDtoAssembler groupUserDtoAssembler;
    
    @Override
    public void save(UserGroupCreateCommand userGroupCommand) {
        UserGroup userGroup = userGroupDtoAssembler.toUserGroup(userGroupCommand);
        UserGroupCreateSpecification specification = new UserGroupCreateSpecification(userGroupRepository);
        specification.isSatisfiedBy(userGroup);
        userGroupRepository.store(userGroup);
    }
    
    @Override
    public void update(UserGroupUpdateCommand userGroupCommand) {
        UserGroup userGroup = userGroupDtoAssembler.toUserGroup(userGroupCommand);
        UserGroupCreateSpecification specification = new UserGroupCreateSpecification(userGroupRepository);
        specification.isSatisfiedBy(userGroup);
        userGroupRepository.store(userGroup);
    }
    
    @Override
    public void deleteBatch(List<String> ids) {
        List<UserGroupId> userGroupIds = new ArrayList<>();
        ids.forEach(id -> userGroupIds.add(new UserGroupId(id)));
        userGroupRepository.remove(userGroupIds);
    }
    
    @Override
    public void disable(String id) {
        UserGroup userGroup = userGroupRepository.find(new UserGroupId(id));
        userGroup.disable();
        userGroupRepository.store(userGroup);
    }
    
    @Override
    public void updateUser(GroupUserCommand command) {
        UserGroup userGroup = userGroupRepository.find(new UserGroupId(command.getGroupId()));
        int count = command.getUserIds().size();
        userGroupRepository.store(userGroup);
        GroupUser groupUser = groupUserDtoAssembler.toUser(command);
        groupUserRepository.store(groupUser);
    }
}
