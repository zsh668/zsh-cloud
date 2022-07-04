package com.zsh.cloud.system.application;

import com.zsh.cloud.system.application.command.RoleAuthorityCommand;
import com.zsh.cloud.system.application.command.RoleCreateCommand;
import com.zsh.cloud.system.application.command.RoleUpdateCommand;

import java.util.List;

/**
 * 角色应用服务接口.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/31 10:47
 */
public interface RoleApplicationService {
    
    /**
     * 保存角色.
     *
     * @param roleCommand
     */
    void save(RoleCreateCommand roleCommand);
    
    /**
     * 更新角色.
     *
     * @param roleCommand
     */
    void update(RoleUpdateCommand roleCommand);
    
    /**
     * 批量删除.
     *
     * @param ids
     */
    void deleteBatch(List<String> ids);
    
    /**
     * 启用、禁用.
     *
     * @param id
     */
    void disable(String id);
    
    void saveRoleAuthority(RoleAuthorityCommand roleCommand);
}
