package com.zsh.cloud.system.domain.model.log.login;

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
}