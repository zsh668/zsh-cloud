package com.zsh.cloud.system.application.impl;

import com.zsh.cloud.system.application.RoleApplicationService;
import com.zsh.cloud.system.application.command.RoleCreateCommand;
import com.zsh.cloud.system.application.command.RoleUpdateCommand;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色应用服务实现类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/31 12:18
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class RoleApplicationServiceImpl implements RoleApplicationService {
    
    @Override
    public void save(RoleCreateCommand roleCommand) {
    
    }
    
    @Override
    public void update(RoleUpdateCommand roleCommand) {
    
    }
    
    @Override
    public void deleteBatch(List<String> roleIds) {
    
    }
    
    @Override
    public void disable(String id) {
    
    }
}
