package com.zsh.cloud.system.application.impl;

import com.zsh.cloud.system.application.UserGroupApplicationService;
import com.zsh.cloud.system.application.assembler.UserGroupDtoAssembler;
import com.zsh.cloud.system.application.command.UserGroupCreateCommand;
import com.zsh.cloud.system.application.command.UserGroupUpdateCommand;
import com.zsh.cloud.system.domain.model.user.User;
import com.zsh.cloud.system.domain.model.user.UserId;
import com.zsh.cloud.system.domain.model.usergroup.UserGroup;
import com.zsh.cloud.system.domain.model.usergroup.UserGroupId;
import com.zsh.cloud.system.domain.model.usergroup.UserGroupRepository;
import com.zsh.cloud.system.domain.specification.UserDeleteSpecification;
import com.zsh.cloud.system.domain.specification.UserGroupCreateSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class UserGroupApplicationServiceImpl implements UserGroupApplicationService {
    
    @Autowired
    private UserGroupRepository userGroupRepository;
    
    @Autowired
    private UserGroupDtoAssembler userGroupDtoAssembler;
    
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
}
