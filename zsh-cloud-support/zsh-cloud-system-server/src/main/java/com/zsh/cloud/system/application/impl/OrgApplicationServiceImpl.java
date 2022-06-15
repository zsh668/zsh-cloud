package com.zsh.cloud.system.application.impl;

import com.zsh.cloud.system.application.OrgApplicationService;
import com.zsh.cloud.system.application.command.OrgCreateCommand;
import com.zsh.cloud.system.application.command.OrgUpdateCommand;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 组织应用服务实现类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/14 14:24
 */
@Service
public class OrgApplicationServiceImpl implements OrgApplicationService {
    
    @Override
    public void save(OrgCreateCommand orgCommand) {
    
    }
    
    @Override
    public void update(OrgUpdateCommand orgCommand) {
    
    }
    
    @Override
    public void deleteBatch(List<String> ids) {
    
    }
    
    @Override
    public void disable(String id) {
    
    }
}
