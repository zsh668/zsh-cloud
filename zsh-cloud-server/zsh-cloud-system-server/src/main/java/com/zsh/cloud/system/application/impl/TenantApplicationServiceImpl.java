package com.zsh.cloud.system.application.impl;

import com.zsh.cloud.system.application.TenantApplicationService;
import com.zsh.cloud.system.application.command.TenantCreateCommand;
import com.zsh.cloud.system.application.command.TenantUpdateCommand;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 租户应用服务实现类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/26 11:22
 */
@Service
public class TenantApplicationServiceImpl implements TenantApplicationService {
    
    @Override
    public void save(TenantCreateCommand tenantCommand) {
    
    }
    
    @Override
    public void update(TenantUpdateCommand tenantCommand) {
    
    }
    
    @Override
    public void deleteBatch(List<String> ids) {
    
    }
    
    @Override
    public void disable(String id) {
    
    }
}
