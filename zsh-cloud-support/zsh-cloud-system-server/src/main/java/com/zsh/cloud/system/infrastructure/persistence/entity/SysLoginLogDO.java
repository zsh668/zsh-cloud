package com.zsh.cloud.system.infrastructure.persistence.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zsh.cloud.common.mybatis.core.entity.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 登录日志DO.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/25 09:45
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_login_log")
public class SysLoginLogDO extends BaseDO {
    
    /**
     * 操作IP
     */
    private String requestIp;
    
    /**
     * 登录人ID
     */
    private String userId;
    
    /**
     * 登录人姓名
     */
    private String userName;
    
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
