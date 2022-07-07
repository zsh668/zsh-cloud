package com.zsh.cloud.system.application;

import com.zsh.cloud.system.application.model.command.TenantCreateCommand;
import com.zsh.cloud.system.application.model.command.TenantUpdateCommand;

import java.util.List;

/**
 * 租户应用服务接口.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/26 11:16
 */
public interface TenantApplicationService {
    
    /**
     * 保存.
     *
     * @param tenantCommand
     */
    void save(TenantCreateCommand tenantCommand);
    
    /**
     * 更新.
     *
     * @param tenantCommand
     */
    void update(TenantUpdateCommand tenantCommand);
    
    /**
     * 批量删除.
     *
     * @param ids
     */
    void deleteBatch(List<String> ids);
    
    /**
     * 禁用.
     *
     * @param id
     */
    void disable(String id);
}
