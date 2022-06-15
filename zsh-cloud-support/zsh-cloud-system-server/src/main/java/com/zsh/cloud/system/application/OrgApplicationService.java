package com.zsh.cloud.system.application;

import com.zsh.cloud.system.application.command.OrgCreateCommand;
import com.zsh.cloud.system.application.command.OrgUpdateCommand;

import java.util.List;

/**
 * 组织应用服务接口.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/14 14:22
 */
public interface OrgApplicationService {
    
    /**
     * 保存.
     *
     * @param orgCommand
     */
    void save(OrgCreateCommand orgCommand);
    
    /**
     * 更新.
     *
     * @param orgCommand
     */
    void update(OrgUpdateCommand orgCommand);
    
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
