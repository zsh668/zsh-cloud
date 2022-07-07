package com.zsh.cloud.system.application;

import com.zsh.cloud.system.application.model.command.UserGroupCreateCommand;
import com.zsh.cloud.system.application.model.command.UserGroupUpdateCommand;

import java.util.List;

/**
 * 用户组应用服务接口.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/7 18:34
 */
public interface UserGroupApplicationService {
    
    /**
     * 保存.
     *
     * @param userGroupCommand
     */
    void save(UserGroupCreateCommand userGroupCommand);
    
    /**
     * 更新.
     *
     * @param userGroupCommand
     */
    void update(UserGroupUpdateCommand userGroupCommand);
    
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
}
