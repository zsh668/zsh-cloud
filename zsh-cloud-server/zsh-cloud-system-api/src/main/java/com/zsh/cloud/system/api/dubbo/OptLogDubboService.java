package com.zsh.cloud.system.api.dubbo;

import com.zsh.cloud.system.api.dto.OptLogDTO;

/**
 * 系统日志保存服务接口.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/27 15:11
 */
public interface OptLogDubboService {
    
    void save(OptLogDTO optLog);
}
