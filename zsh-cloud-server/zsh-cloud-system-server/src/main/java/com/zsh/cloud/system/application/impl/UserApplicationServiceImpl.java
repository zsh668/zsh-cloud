package com.zsh.cloud.system.application.impl;

import com.zsh.cloud.system.application.UserApplicationService;
import com.zsh.cloud.system.application.assembler.UserDtoAssembler;
import com.zsh.cloud.system.application.command.PasswordCommand;
import com.zsh.cloud.system.application.command.UserCreateCommand;
import com.zsh.cloud.system.application.command.UserUpdateCommand;
import com.zsh.cloud.system.domain.model.tenant.TenantRepository;
import com.zsh.cloud.system.domain.model.user.User;
import com.zsh.cloud.system.domain.model.user.UserId;
import com.zsh.cloud.system.domain.model.user.UserRepository;
import com.zsh.cloud.system.domain.specification.UserCreateSpecification;
import com.zsh.cloud.system.domain.specification.UserDeleteSpecification;
import com.zsh.cloud.system.domain.specification.UserUpdateSpecification;
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
    
    @Autowired
    private TenantRepository tenantRepository;
    
    @Override
    public void save(UserCreateCommand userCommand) {
        User user = userDtoAssembler.toUser(userCommand);
        UserCreateSpecification specification = new UserCreateSpecification(userRepository);
        specification.isSatisfiedBy(user);
        userRepository.store(user);
    }
    
    @Override
    public void update(UserUpdateCommand userCommand) {
        User user = userDtoAssembler.toUser(userCommand);
        UserUpdateSpecification specification = new UserUpdateSpecification(userRepository);
        specification.isSatisfiedBy(user);
        userRepository.store(user);
    }
    
    @Override
    public void deleteBatch(List<String> ids) {
        List<UserId> userIds = new ArrayList<>();
        ids.forEach(id -> userIds.add(new UserId(id)));
        UserDeleteSpecification specification = new UserDeleteSpecification(tenantRepository);
        for (UserId userId : userIds) {
            User user = userRepository.find(userId);
            specification.isSatisfiedBy(user);
        }
        userRepository.remove(userIds);
    }
    
    @Override
    public void disable(String id) {
        User user = userRepository.find(new UserId(id));
        UserDeleteSpecification specification = new UserDeleteSpecification(tenantRepository);
        specification.isSatisfiedBy(user);
        user.disable();
        userRepository.store(user);
    }
    
    @Override
    public void changePassword(PasswordCommand passwordCommand) {
        User user = userRepository.find(new UserId(passwordCommand.getUserId()));
        user.changePassword(passwordCommand.getPassword(), passwordCommand.getNewPassword());
        user.expand();
        userRepository.store(user);
    }
    
    @Override
    public void resetPassword(List<String> ids) {
        userRepository.reset(ids);
    }
}
