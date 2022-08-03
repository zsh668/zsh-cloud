package com.zsh.cloud.sms.infrastructure.persistence.entity;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zsh.cloud.common.tenant.entity.TenantBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 人工处理任务表DO.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/28 17:45
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sms_manual_process")
public class SmsManualProcessDO extends TenantBaseDO {
    
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
     * 通道id集合.
     */
    private String configIds;
    
    /**
     * 状态 0新建，1处理中，2处理成功，3处理失败.
     */
    private Integer status;
    
    /**
     * 功能描述.
     */
    @TableField(value = "describe_", condition = SqlCondition.LIKE)
    private String describe;
}
