package com.zsh.cloud.system.application;

import com.zsh.cloud.system.application.model.command.MenuCreateCommand;
import com.zsh.cloud.system.application.model.command.MenuUpdateCommand;

import java.util.List;

/**
 * 菜单应用服务接口.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/17 15:01
 */
public interface MenuApplicationService {
    
    /**
     * 保存.
     *
     * @param menuCommand
     */
    void save(MenuCreateCommand menuCommand);
    
    /**
     * 更新.
     *
     * @param menuCommand
     */
    void update(MenuUpdateCommand menuCommand);
    
    /**
     * 批量删除.
     *
     * @param ids
     */
    void deleteBatch(List<String> ids);
    
    /**
     * 开启、禁用.
     *
     * @param id
     */
    void disable(String id);
}
