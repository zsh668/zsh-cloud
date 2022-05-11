package com.zsh.cloud.common.log.config;

import com.zsh.cloud.common.log.aop.SysLogAspect;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 日志自动配置.
 * <p>
 * 启动条件： 1，存在web环境 2，配置文件中zsh.cloud.log.enabled=true 3，配置文件中不存在：zsh.cloud.log.enabled 值
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/9 17:30
 */
@EnableAsync
@Configuration
@AllArgsConstructor
@ConditionalOnWebApplication
@ConditionalOnProperty(name = "zsh.cloud.log.enabled", havingValue = "true", matchIfMissing = true)
public class LogAutoConfiguration {
    
    @Bean
    @ConditionalOnMissingBean
    public SysLogAspect sysLogAspect() {
        return new SysLogAspect();
    }
}
