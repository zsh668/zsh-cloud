package com.zsh.cloud.common.log.event;

import com.zsh.cloud.system.api.dto.OptLogDTO;
import org.springframework.context.ApplicationEvent;

/**
 * 系统日志事件.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/9 15:53
 **/
public class SysLogEvent extends ApplicationEvent {
    
    public SysLogEvent(OptLogDTO source) {
        super(source);
    }
}
