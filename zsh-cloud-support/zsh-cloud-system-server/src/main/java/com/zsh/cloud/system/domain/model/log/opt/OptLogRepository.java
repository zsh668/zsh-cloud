package com.zsh.cloud.system.domain.model.log.opt;

import com.zsh.cloud.system.domain.model.log.LogId;

/**
 * 操作日志-Repository接口.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/21 17:17
 */
public interface OptLogRepository {
    
    /**
     * 保存.
     *
     * @param optLog
     */
    void store(OptLog optLog);
    
    /**
     * 根据ID获取.
     *
     * @param logId
     * @return
     */
    OptLog find(LogId logId);
}