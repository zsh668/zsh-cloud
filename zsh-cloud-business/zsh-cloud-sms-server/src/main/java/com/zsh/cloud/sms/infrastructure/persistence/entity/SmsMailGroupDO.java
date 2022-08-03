package com.zsh.cloud.sms.infrastructure.persistence.entity;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zsh.cloud.common.tenant.entity.TenantBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 通讯组DO.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/28 17:45
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sms_mail_group")
public class SmsMailGroupDO extends TenantBaseDO {
    
    /**
     * 名称.
     */
    private String groupName;
    
    /**
     * 类型.
     */
    private String type;
    
    /**
     * 功能描述.
     */
    @TableField(value = "describe_", condition = SqlCondition.LIKE)
    private String describe;
}
