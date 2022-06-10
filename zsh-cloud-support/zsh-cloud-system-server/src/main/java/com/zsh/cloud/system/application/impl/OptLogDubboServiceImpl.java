package com.zsh.cloud.system.application.impl;

import com.zsh.cloud.common.core.util.JsonUtils;
import com.zsh.cloud.system.api.dto.OptLogDTO;
import com.zsh.cloud.system.api.dubbo.OptLogDubboService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * 系统日志保存服务实现.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/27 15:14
 */
@Slf4j
@DubboService(timeout = 3000)
public class OptLogDubboServiceImpl implements OptLogDubboService {
    
    @Override
    public void save(OptLogDTO optLog) {
        log.info(JsonUtils.toJsonString(optLog));
    }
}
