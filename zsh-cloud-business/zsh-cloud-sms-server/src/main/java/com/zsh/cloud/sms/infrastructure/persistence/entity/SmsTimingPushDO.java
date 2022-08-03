package com.zsh.cloud.sms.infrastructure.persistence.entity;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zsh.cloud.common.tenant.entity.TenantBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 定时发送DO.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/28 17:45
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sms_timing_push")
public class SmsTimingPushDO extends TenantBaseDO {
    
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
     * 参数json.
     */
    private String request;
    
    /**
     * 发送时间.
     */
    private String timing;
    
    /**
     * 状态 0：未处理 1：已处理.
     */
    private Integer status;
    
    /**
     * 描述.
     */
    @TableField(value = "describe_", condition = SqlCondition.LIKE)
    private String describe;
}
