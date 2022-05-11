package com.zsh.cloud.common.exception.config;

import com.zsh.cloud.common.exception.handler.GlobalExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 统一异常处理配置.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/9 11:41
 */
@Configuration
public class ExceptionConfig {
    
    @Bean
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }
}
