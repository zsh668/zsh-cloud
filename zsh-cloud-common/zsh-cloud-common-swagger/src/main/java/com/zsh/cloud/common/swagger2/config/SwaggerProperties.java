package com.zsh.cloud.common.swagger2.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotEmpty;

/**
 * Swagger 配置属性.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/9 10:38
 */
@ConfigurationProperties("zsh.cloud.swagger")
@Data
public class SwaggerProperties {
    
    /**
     * 标题
     */
    @NotEmpty(message = "标题不能为空")
    private String title;
    
    /**
     * 描述
     */
    @NotEmpty(message = "描述不能为空")
    private String description;
    
    /**
     * 作者
     */
    @NotEmpty(message = "作者不能为空")
    private String author;
    
    /**
     * 服务地址
     */
    @NotEmpty(message = "服务地址不能为空")
    private String termsOfServiceUrl;
    
    /**
     * 版本
     */
    @NotEmpty(message = "版本不能为空")
    private String version;
    
    /**
     * 扫描的包
     */
    @NotEmpty(message = "扫描的 package 不能为空")
    private String basePackage;
}
