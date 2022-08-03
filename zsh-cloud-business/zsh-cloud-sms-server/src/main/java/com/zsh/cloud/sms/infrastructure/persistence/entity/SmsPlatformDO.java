package com.zsh.cloud.sms.infrastructure.persistence.entity;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zsh.cloud.common.tenant.entity.TenantBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 接入平台DO.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/28 17:45
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sms_platform")
public class SmsPlatformDO extends TenantBaseDO {
    
    /**
     * 平台名称.
     */
    private String platformName;
    
    /**
     * key.
     */
    private String accessKeyId;
    
    /**
     * 秘钥.
     */
    private String accessKeySecret;
    
    /**
     * ip绑定,多个用英文逗号分隔.
     */
    private String ipAddr;
    
    /**
     * 是否鉴权：0不鉴权.
     */
    private Integer needAuth;
    
    /**
     * 是否可用：0不可用.
     */
    private Boolean isActive;
    
    /**
     * 平台等级（预留）.
     */
    private Integer level;
    
    /**
     * 功能描述.
     */
    @TableField(value = "describe_", condition = SqlCondition.LIKE)
    private String describe;
}
