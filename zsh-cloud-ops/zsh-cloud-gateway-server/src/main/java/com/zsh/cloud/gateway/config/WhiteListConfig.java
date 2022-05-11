package com.zsh.cloud.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 白名单配置.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/20 14:22
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "secure.white")
public class WhiteListConfig {
    
    private List<String> urls;
}
