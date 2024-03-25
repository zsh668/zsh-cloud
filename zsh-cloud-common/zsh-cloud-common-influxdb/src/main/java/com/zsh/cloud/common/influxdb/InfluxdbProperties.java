package com.zsh.cloud.common.influxdb;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * influxdb配置.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2024/3/25 13:44
 */
@Data
@ConfigurationProperties(prefix = "zsh.cloud.influxdb")
public class InfluxdbProperties {
    
    /**
     * 用户名
     */
    private String username;
    
    /**
     * 密码
     */
    private String password;
    
    /**
     * 连接地址
     */
    private String url;
    
    /**
     * 数据库名
     */
    private String database;
}
