package com.zsh.cloud.sms.infrastructure.persistence.entity;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zsh.cloud.common.tenant.entity.TenantBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 配置—签名表DO.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/28 17:45
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sms_config_signature")
public class SmsConfigSignatureDO extends TenantBaseDO {
    
    /**
     * 配置主键.
     */
    private String configId;
    
    /**
     * 签名主键.
     */
    private String signatureId;
    
    /**
     * 通道签名（如果为空在不需要签名id）.
     */
    private String configSignatureCode;
    
    /**
     * 功能描述.
     */
    @TableField(value = "describe_", condition = SqlCondition.LIKE)
    private String describe;
}
