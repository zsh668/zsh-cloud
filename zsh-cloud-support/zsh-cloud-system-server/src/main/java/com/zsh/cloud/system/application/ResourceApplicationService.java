package com.zsh.cloud.system.application;

import com.zsh.cloud.system.application.model.command.ResourceCreateCommand;
import com.zsh.cloud.system.application.model.command.ResourceUpdateCommand;

import java.util.List;

/**
 * 资源应用服务接口.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/17 15:01
 */
public interface ResourceApplicationService {
    
    /**
     * 保存
     *
     * @param resourceCommand
     */
    void save(ResourceCreateCommand resourceCommand);
    
    /**
     * 更新.
     *
     * @param resourceCommand
     */
    void update(ResourceUpdateCommand resourceCommand);
    
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
