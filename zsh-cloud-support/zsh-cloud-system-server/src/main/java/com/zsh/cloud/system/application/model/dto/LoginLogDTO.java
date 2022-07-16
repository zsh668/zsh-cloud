package com.zsh.cloud.system.application.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 登录日志DTO.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/29 10:30
 */
@Data
@Accessors(chain = true)
public class LoginLogDTO implements Serializable {
    
    /**
     * 操作IP
     */
    private String requestIp;
    
    /**
     * 登录人账号
     */
    private String account;
    
    /**
     * 登录描述
     */
    private String description;
    
    /**
     * 登录时间
     */
    private LocalDateTime loginTime;
    
    /**
     * 浏览器请求头
     */
    private String ua;
    
    /**
     * 浏览器名称
     */
    private String browser;
    
    /**
     * 浏览器版本
     */
    private String browserVersion;
    
    /**
     * 操作系统
     */
    private String operatingSystem;
    
    /**
     * 登录地点
     */
    private String location;
}
