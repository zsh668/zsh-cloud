package com.zsh.cloud.system.infrastructure.persistence.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zsh.cloud.common.mybatis.core.entity.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 系统日志DO.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/25 09:45
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_opt_log")
public class SysOptLogDO extends BaseDO {
    
    /**
     * 操作IP.
     */
    private String requestIp;
    
    /**
     * 日志类型 #LogType{OPT:操作类型;EX:异常类型}.
     */
    private String type;
    
    /**
     * 操作人.
     */
    private String userName;
    
    /**
     * 操作描述.
     */
    private String description;
    
    /**
     * 类路径.
     */
    private String classPath;
    
    /**
     * 请求方法.
     */
    private String actionMethod;
    
    /**
     * 请求地址.
     */
    private String requestUri;
    
    /**
     * 请求类型 #HttpMethod{GET:GET请求;POST:POST请求;PUT:PUT请求;DELETE:DELETE请求;PATCH:PATCH请求;TRACE:TRACE请求;HEAD:HEAD请求;OPTIONS:OPTIONS请求;}.
     */
    private String httpMethod;
    
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
}
