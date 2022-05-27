package com.zsh.cloud.common.exception.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 统一异常处理配置.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/9 11:41
 */
@Configuration
@ComponentScan(basePackages = "com.zsh.cloud.common.exception")
public class ExceptionConfig {

}
