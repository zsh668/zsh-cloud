package com.zsh.cloud.system.application;

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
    
    void save(RoleCreateCommand roleCommand);
    
    void update(RoleUpdateCommand roleCommand);
    
    void deleteBatch(List<String> roleIds);
    
    void disable(String id);
}
