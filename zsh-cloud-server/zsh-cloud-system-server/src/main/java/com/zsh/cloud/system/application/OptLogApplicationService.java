package com.zsh.cloud.system.application;

import com.zsh.cloud.common.log.dto.OptLogDTO;

/**
 * 系统日志应用服务接口.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/9 12:00
 */
public interface OptLogApplicationService {
    
    void save(OptLogDTO optLog);
}
