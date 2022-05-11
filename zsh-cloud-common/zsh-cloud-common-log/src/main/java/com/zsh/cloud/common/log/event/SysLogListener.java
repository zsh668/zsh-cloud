package com.zsh.cloud.common.log.event;

import com.zsh.cloud.common.log.dto.OptLogDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

import java.util.function.Consumer;

/**
 * 异步监听日志事件.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/9 15:53
 */
@Slf4j
@AllArgsConstructor
public class SysLogListener {
    
    private Consumer<OptLogDTO> consumer;
    
    /**
     * 保存日志.
     *
     * @param event
     */
    @Async
    @Order
    @EventListener(SysLogEvent.class)
    public void saveSysLog(SysLogEvent event) {
        OptLogDTO optLog = (OptLogDTO) event.getSource();
        consumer.accept(optLog);
    }
}
