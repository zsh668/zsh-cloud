package com.zsh.cloud.system.domain.model.log.login;

import com.zsh.cloud.system.domain.model.log.LogId;

import java.util.List;

/**
 * 登录日志-Repository接口.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/21 17:17
 */
public interface LoginLogRepository {
    
    /**
     * 保存.
     *
     * @param loginLog
     */
    void store(LoginLog loginLog);
    
    /**
     * 根据ID获取.
     *
     * @param logId
     * @return
     */
    LoginLog find(LogId logId);
    
    /**
     * 删除.
     *
     * @param logIds
     */
    void remove(List<LogId> logIds);
}