package com.zsh.cloud.system.application.impl;

import com.zsh.cloud.system.application.UserApplicationService;
import com.zsh.cloud.system.application.assembler.UserDtoAssembler;
import com.zsh.cloud.system.application.command.PasswordCommand;
import com.zsh.cloud.system.application.command.ResetPasswordCommand;
import com.zsh.cloud.system.application.command.UserCreateCommand;
import com.zsh.cloud.system.application.command.UserUpdateCommand;
import com.zsh.cloud.system.domain.model.role.RoleId;
import com.zsh.cloud.system.domain.model.user.User;
import com.zsh.cloud.system.domain.model.user.UserRepository;
import com.zsh.cloud.system.domain.specification.UserCreateSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户应用服务实现类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/9 18:51
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserApplicationServiceImpl implements UserApplicationService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserDtoAssembler userDtoAssembler;
    
    @Override
    public void save(UserCreateCommand userCommand) {
        List<RoleId> roleIdList = new ArrayList<>();
        if (userCommand.getRoleIdList() != null) {
            userCommand.getRoleIdList().forEach(roleId -> {
                roleIdList.add(new RoleId(roleId));
            });
        }
        User user = userDtoAssembler.toUser(userCommand, roleIdList);
        UserCreateSpecification specification = new UserCreateSpecification(userRepository);
        specification.isSatisfiedBy(user);
        userRepository.store(user);
    }
    
    @Override
    public void update(UserUpdateCommand userCommand) {
    
    }
    
    @Override
    public void deleteBatch(List<String> ids) {
    
    }
    
    @Override
    public void disable(String id) {
    
    }
    
    @Override
    public void changePassword(PasswordCommand passwordCommand) {
    
    }
    
    @Override
    public void resetPassword(ResetPasswordCommand command) {
    
    }
}
