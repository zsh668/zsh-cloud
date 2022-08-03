package com.zsh.cloud.sms.infrastructure.persistence.entity;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zsh.cloud.common.tenant.entity.TenantBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 模板DO.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/28 17:45
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sms_template")
public class SmsTemplateDO extends TenantBaseDO {
    
    /**
     * 模板名称.
     */
    private String templateName;
    
    /**
     * 模板编码.
     */
    private String templateCode;
    
    /**
     * 模板内容.
     */
    private String content;
    
    /**
     * 模板类型 1：验证码，2：营销类.
     */
    private Integer type;
    
    /**
     * 描述.
     */
    @TableField(value = "describe_", condition = SqlCondition.LIKE)
    private String describe;
}
