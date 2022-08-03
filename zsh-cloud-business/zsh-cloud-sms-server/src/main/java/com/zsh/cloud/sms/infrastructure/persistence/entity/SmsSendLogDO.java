package com.zsh.cloud.sms.infrastructure.persistence.entity;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zsh.cloud.common.tenant.entity.TenantBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 发送日志DO.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/28 17:45
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sms_send_log")
public class SmsSendLogDO extends TenantBaseDO {
    
    /**
     * 配置主键.
     */
    private String configId;
    
    /**
     * 配置平台.
     */
    private String configPlatform;
    
    /**
     * 配置名称.
     */
    private String configName;
    
    /**
     * 模板.
     */
    private String template;
    
    /**
     * 签名.
     */
    private String signature;
    
    /**
     * 手机号.
     */
    private String mobile;
    
    /**
     * 请求参数.
     */
    private String request;
    
    /**
     * 返回参数.
     */
    private String response;
    
    /**
     * 错误信息.
     */
    private String error;
    
    /**
     * 耗时.
     */
    private Long useTime;
    
    /**
     * 状态：0失败，1成功.
     */
    private Integer status;
    
    /**
     * api日志主键.
     */
    private String apiLogId;
    
    /**
     * 功能描述.
     */
    @TableField(value = "describe_", condition = SqlCondition.LIKE)
    private String describe;
}
