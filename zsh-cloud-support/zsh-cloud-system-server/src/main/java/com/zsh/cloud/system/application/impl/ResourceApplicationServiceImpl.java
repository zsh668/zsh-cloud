package com.zsh.cloud.system.application.impl;

import com.zsh.cloud.system.application.ResourceApplicationService;
import com.zsh.cloud.system.application.command.ResourceCreateCommand;
import com.zsh.cloud.system.application.command.ResourceUpdateCommand;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资源应用服务实现类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/17 16:35
 */
@Service
public class ResourceApplicationServiceImpl implements ResourceApplicationService {
    
    @Override
    public void save(ResourceCreateCommand resourceCommand) {
    
    }
    
    @Override
    public void update(ResourceUpdateCommand resourceCommand) {
    
    }
    
    @Override
    public void deleteBatch(List<String> ids) {
    
    }
    
    @Override
    public void disable(String id) {
    
    }
}
