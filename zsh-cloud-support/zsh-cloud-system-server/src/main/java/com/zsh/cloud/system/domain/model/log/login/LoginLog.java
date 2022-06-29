package com.zsh.cloud.system.domain.model.log.login;

import com.zsh.cloud.common.core.domain.Entity;
import com.zsh.cloud.system.domain.model.log.LogId;
import com.zsh.cloud.system.domain.model.user.Account;
import com.zsh.cloud.system.domain.model.user.UserId;
import com.zsh.cloud.system.domain.model.user.UserName;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 登录日志.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/21 17:18
 */
@Getter
public class LoginLog implements Entity<LoginLog> {
    
    /**
     * 日志ID.
     */
    private LogId logId;
    
    /**
     * 操作IP
     */
    private String requestIp;
    
    /**
     * 登录人ID
     */
    private UserId userId;
    
    /**
     * 登录人姓名
     */
    private UserName userName;
    
    /**
     * 登录人账号
     */
    private Account account;
    
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
    
    public LoginLog(String requestIp, Account account, String description, LocalDateTime loginTime, String ua,
            String browser, String browserVersion, String operatingSystem, String location) {
        this.requestIp = requestIp;
        this.account = account;
        this.description = description;
        this.loginTime = loginTime;
        this.ua = ua;
        this.browser = browser;
        this.browserVersion = browserVersion;
        this.operatingSystem = operatingSystem;
        this.location = location;
    }
    
    @Override
    public boolean sameIdentityAs(LoginLog other) {
        return other != null && logId.sameValueAs(other.logId);
    }
}
