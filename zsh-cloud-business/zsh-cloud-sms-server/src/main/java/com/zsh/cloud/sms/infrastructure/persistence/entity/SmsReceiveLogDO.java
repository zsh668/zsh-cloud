package com.zsh.cloud.sms.infrastructure.persistence.entity;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zsh.cloud.common.tenant.entity.TenantBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 接收日志DO.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/28 17:45
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sms_receive_log")
public class SmsReceiveLogDO extends TenantBaseDO {
    
    /**
     * 请求平台id.
     */
    private String platformId;
    
    /**
     * 请求平台名称.
     */
    private String platformName;
    
    /**
     * 请求平台业务信息.
     */
    private String business;
    
    /**
     * 配置主键集合.
     */
    private String configIds;
    
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
     * 日志id.
     */
    private String apiLogId;
    
    /**
     * 功能描述.
     */
    @TableField(value = "describe_", condition = SqlCondition.LIKE)
    private String describe;
}
