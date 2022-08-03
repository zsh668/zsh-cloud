package com.zsh.cloud.sms.infrastructure.persistence.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zsh.cloud.common.tenant.entity.TenantBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 通讯录-通讯组DO.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/28 17:45
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sms_mail_list_group")
public class SmsMailListGroupDO extends TenantBaseDO {
    
    /**
     * 通讯录id.
     */
    private String listId;
    
    /**
     * 通讯组id.
     */
    private String groupId;
}
