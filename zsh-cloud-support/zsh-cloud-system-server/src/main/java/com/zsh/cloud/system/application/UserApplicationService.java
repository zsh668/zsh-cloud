package com.zsh.cloud.system.application;

import com.zsh.cloud.system.application.model.command.PasswordCommand;
import com.zsh.cloud.system.application.model.command.UserCreateCommand;
import com.zsh.cloud.system.application.model.command.UserUpdateCommand;

import java.util.List;

/**
 * 用户应用服务接口.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/9 12:00
 */
public interface UserApplicationService {
    
    /**
     * 保存用户.
     *
     * @param userCommand
     */
    void save(UserCreateCommand userCommand);
    
    /**
     * 更新用户.
     *
     * @param userCommand
     */
    void update(UserUpdateCommand userCommand);
    
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
    
    /**
     * 修改密码
     *
     * @param passwordCommand
     */
    void changePassword(PasswordCommand passwordCommand);
    
    /**
     * 批量重置密码.
     *
     * @param ids
     */
    void resetPassword(List<String> ids);
}
