package com.zsh.cloud.system.domain.model.log.opt;

import com.zsh.cloud.common.core.domain.Entity;
import com.zsh.cloud.common.log.enums.LogTypeEnum;
import com.zsh.cloud.system.domain.model.log.LogId;
import com.zsh.cloud.system.domain.model.tenant.TenantId;
import com.zsh.cloud.system.domain.model.user.UserName;
import lombok.Getter;
import org.springframework.http.HttpMethod;

import java.time.LocalDateTime;

/**
 * 操作日志.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/21 17:09
 */
@Getter
public class OptLog implements Entity<OptLog> {
    
    /**
     * 日志ID.
     */
    private LogId logId;
    
    /**
     * 操作IP.
     */
    private String requestIp;
    
    /**
     * 日志类型. #LogType{OPT:操作类型;EX:异常类型}
     */
    private LogTypeEnum type;
    
    /**
     * 操作人
     */
    private UserName userName;
    
    /**
     * 操作描述.
     */
    private String description;
    
    /**
     * 类路径.
     */
    private String classPath;
    
    /**
     * 请求类型.
     */
    private String actionMethod;
    
    /**
     * 请求地址.
     */
    private String requestUri;
    
    /**
     * 请求类型. #HttpMethod{GET:GET请求;POST:POST请求;PUT:PUT请求;DELETE:DELETE请求;PATCH:PATCH请求;TRACE:TRACE请求;HEAD:HEAD请求;OPTIONS:OPTIONS请求;}
     */
    private HttpMethod httpMethod;
    
    /**
     * 请求参数.
     */
    private String params;
    
    /**
     * 返回值.
     */
    private String result;
    
    /**
     * 异常详情信息.
     */
    private String exDesc;
    
    /**
     * 异常描述.
     */
    private String exDetail;
    
    /**
     * 开始时间.
     */
    private LocalDateTime startTime;
    
    /**
     * 完成时间.
     */
    private LocalDateTime finishTime;
    
    /**
     * 消耗时间.
     */
    private Long consumingTime;
    
    /**
     * 浏览器.
     */
    private String ua;
    
    /**
     * 租户ID.
     */
    private TenantId tenantId;
    
    public OptLog(String requestIp, LogTypeEnum type, UserName userName, String description, String classPath,
            String actionMethod, String requestUri, HttpMethod httpMethod, String params, String result, String exDesc,
            String exDetail, LocalDateTime startTime, LocalDateTime finishTime, Long consumingTime, String ua,
            TenantId tenantId) {
        this.requestIp = requestIp;
        this.type = type;
        this.userName = userName;
        this.description = description;
        this.classPath = classPath;
        this.actionMethod = actionMethod;
        this.requestUri = requestUri;
        this.httpMethod = httpMethod;
        this.params = params;
        this.result = result;
        this.exDesc = exDesc;
        this.exDetail = exDetail;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.consumingTime = consumingTime;
        this.ua = ua;
        this.tenantId = tenantId;
    }
    
    public OptLog(LogId logId, String requestIp, LogTypeEnum type, UserName userName, String description,
            String classPath, String actionMethod, String requestUri, HttpMethod httpMethod, String params,
            String result, String exDesc, String exDetail, LocalDateTime startTime, LocalDateTime finishTime,
            Long consumingTime, String ua) {
        this.logId = logId;
        this.requestIp = requestIp;
        this.type = type;
        this.userName = userName;
        this.description = description;
        this.classPath = classPath;
        this.actionMethod = actionMethod;
        this.requestUri = requestUri;
        this.httpMethod = httpMethod;
        this.params = params;
        this.result = result;
        this.exDesc = exDesc;
        this.exDetail = exDetail;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.consumingTime = consumingTime;
        this.ua = ua;
    }
    
    @Override
    public boolean sameIdentityAs(OptLog other) {
        return other != null && logId.sameValueAs(other.logId);
    }
}
