package com.zsh.cloud.sms.infrastructure.persistence.entity;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zsh.cloud.common.tenant.entity.TenantBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 配置表DO.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/28 17:45
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sms_config")
public class SmsConfigDO extends TenantBaseDO {
    
    /**
     * 名称.
     */
    private String configName;
    
    /**
     * 平台.
     */
    private String platform;
    
    /**
     * 域名.
     */
    private String domain;
    
    /**
     * key.
     */
    private String accessKeyId;
    
    /**
     * 秘钥.
     */
    private String accessKeySecret;
    
    /**
     * 其他配置 json格式.
     */
    private String other;
    
    /**
     * 是否可用：0不可用.
     */
    private Boolean isActive;
    
    /**
     * 状态 1启用，0禁用.
     */
    private Boolean status;
    
    /**
     * 级别.
     */
    private Integer level;
    
    /**
     * 通道类型，1：文字，2：语音，3：推送.
     */
    private Integer channelType;
    
    /**
     * 功能描述.
     */
    @TableField(value = "describe_", condition = SqlCondition.LIKE)
    private String describe;
}
