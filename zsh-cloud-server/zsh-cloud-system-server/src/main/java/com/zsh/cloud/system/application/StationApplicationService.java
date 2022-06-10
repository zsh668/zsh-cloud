package com.zsh.cloud.system.application;

import com.zsh.cloud.system.application.command.StationCreateCommand;
import com.zsh.cloud.system.application.command.StationUpdateCommand;

import java.util.List;

/**
 * 岗位应用服务接口.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/10 15:49
 */
public interface StationApplicationService {
    
    /**
     * 保存.
     *
     * @param stationCommand
     */
    void save(StationCreateCommand stationCommand);
    
    /**
     * 更新.
     *
     * @param stationCommand
     */
    void update(StationUpdateCommand stationCommand);
    
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
