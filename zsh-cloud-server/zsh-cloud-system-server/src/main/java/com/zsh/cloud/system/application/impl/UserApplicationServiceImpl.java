package com.zsh.cloud.system.application.impl;

import com.zsh.cloud.system.application.UserApplicationService;
import com.zsh.cloud.system.application.command.PasswordCommand;
import com.zsh.cloud.system.application.command.UserCommand;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    
    @Override
    public void save(UserCommand userCommand) {
    
    }
    
    @Override
    public void update(UserCommand userCommand) {
    
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
}
