package com.zsh.cloud.system.config;

import com.zsh.cloud.common.log.dto.OptLogDTO;
import com.zsh.cloud.common.log.event.SysLogListener;
import com.zsh.cloud.system.application.OptLogApplicationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.function.Consumer;

/**
 * 系统操作日志配置类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/9 18:54
 */
@EnableAsync
@Configuration
public class SysLogConfiguration {
    
    /**
     * 创建日志记录监听器对象.
     */
    @Bean
    public SysLogListener sysLogListener(OptLogApplicationService optLogApplicationService) {
        Consumer<OptLogDTO> consumer = optLogApplicationService::save;
        return new SysLogListener(consumer);
    }
}
