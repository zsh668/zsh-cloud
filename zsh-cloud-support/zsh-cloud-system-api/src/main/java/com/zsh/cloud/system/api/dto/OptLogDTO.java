package com.zsh.cloud.system.api.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 操作日志dto.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/9 15:53
 **/
@Data
public class OptLogDTO implements Serializable {
    
    /**
     * 操作IP
     */
    private String requestIp;
    
    /**
     * 日志类型 #LogType{OPT:操作类型;EX:异常类型}
     */
    private String type;
    
    /**
     * 操作人
     */
    private String userName;
    
    /**
     * 操作描述
     */
    private String description;
    
    /**
     * 类路径
     */
    private String classPath;
    
    /**
     * 请求类型
     */
    private String actionMethod;
    
    /**
     * 请求地址
     */
    private String requestUri;
    
    /**
     * 请求类型 #HttpMethod{GET:GET请求;POST:POST请求;PUT:PUT请求;DELETE:DELETE请求;PATCH:PATCH请求;TRACE:TRACE请求;HEAD:HEAD请求;OPTIONS:OPTIONS请求;}
     */
    private String httpMethod;
    
    /**
     * 请求参数
     */
    private String params;
    
    /**
     * 返回值
     */
    private String result;
    
    /**
     * 异常详情信息
     */
    private String exDesc;
    
    /**
     * 异常描述
     */
    private String exDetail;
    
    /**
     * 开始时间
     */
    private LocalDateTime startTime;
    
    /**
     * 完成时间
     */
    private LocalDateTime finishTime;
    
    /**
     * 消耗时间
     */
    private Long consumingTime;
    
    /**
     * 浏览器
     */
    private String ua;
    
    /**
     * 租户ID
     */
    private String tenantId;
}
