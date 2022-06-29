package com.zsh.cloud.system.api.dubbo;

import com.zsh.cloud.system.api.dto.LoginLogDTO;

/**
 * 登录日志保存服务接口.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/27 15:11
 */
public interface LoginLogDubboService {
    
    /**
     * 保存.
     *
     * @param loginLog
     */
    void save(LoginLogDTO loginLog);
}
