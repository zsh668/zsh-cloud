package com.zsh.cloud.sms.infrastructure.persistence.entity;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zsh.cloud.common.tenant.entity.TenantBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 通讯录DO.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/28 17:45
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sms_mail_list")
public class SmsMailListDO extends TenantBaseDO {
    
    /**
     * 姓名.
     */
    private String realname;
    
    /**
     * 部门.
     */
    private String dept;
    
    /**
     * 职位.
     */
    private String position;
    
    /**
     * 电话.
     */
    private String phone;
    
    /**
     * 邮箱.
     */
    private String email;
    
    /**
     * QQ.
     */
    private String qq;
    
    /**
     * 微信.
     */
    private String wechat;
    
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
