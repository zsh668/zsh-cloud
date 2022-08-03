package com.zsh.cloud.sms.infrastructure.persistence.entity;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zsh.cloud.common.tenant.entity.TenantBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Blob;

/**
 * 营销DO.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/28 17:45
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sms_marketing")
public class SmsMarketingDO extends TenantBaseDO {
    
    /**
     * 主题.
     */
    private String marketingTitle;
    
    /**
     * 模板.
     */
    private String template;
    
    /**
     * 签名.
     */
    private String signature;
    
    /**
     * 用户组名称，可能没有.
     */
    private String mailGroup;
    
    /**
     * 手机号:多个用,分隔.
     */
    private Blob mobiles;
    
    /**
     * 手机号个数.
     */
    private Integer mobileNumber;
    
    /**
     * 手机号导入文件名称.
     */
    private String mobileFile;
    
    /**
     * 短信内容.
     */
    private String content;
    
    /**
     * 状态 0：待审核 1：通过 2：驳回.
     */
    private Integer status;
    
    /**
     * 功能描述.
     */
    @TableField(value = "describe_", condition = SqlCondition.LIKE)
    private String describe;
    
    /**
     * 创建人姓名（申请人）.
     */
    private String createdUserName;
    
    /**
     * 修改人姓名（审批人）.
     */
    private String updatedUserName;
}
