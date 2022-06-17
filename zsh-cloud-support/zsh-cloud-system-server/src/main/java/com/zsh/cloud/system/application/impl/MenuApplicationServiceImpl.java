package com.zsh.cloud.system.application.impl;

import com.zsh.cloud.system.application.MenuApplicationService;
import com.zsh.cloud.system.application.command.MenuCreateCommand;
import com.zsh.cloud.system.application.command.MenuUpdateCommand;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单应用服务实现类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/17 16:35
 */
@Service
public class MenuApplicationServiceImpl implements MenuApplicationService {
    
    @Override
    public void save(MenuCreateCommand menuCommand) {
    
    }
    
    @Override
    public void update(MenuUpdateCommand menuCommand) {
    
    }
    
    @Override
    public void deleteBatch(List<String> ids) {
    
    }
    
    @Override
    public void disable(String id) {
    
    }
}
