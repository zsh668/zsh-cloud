package com.zsh.cloud.sms.infrastructure.persistence.entity;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zsh.cloud.common.tenant.entity.TenantBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 签名DO.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/28 17:45
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sms_signature")
public class SmsSignatureDO extends TenantBaseDO {
    
    /**
     * 签名名称.
     */
    private String signatureName;
    
    /**
     * 签名编码.
     */
    private String signatureCode;
    
    /**
     * 签名内容.
     */
    private String content;
    
    /**
     * 备注.
     */
    @TableField(value = "describe_", condition = SqlCondition.LIKE)
    private String describe;
}
