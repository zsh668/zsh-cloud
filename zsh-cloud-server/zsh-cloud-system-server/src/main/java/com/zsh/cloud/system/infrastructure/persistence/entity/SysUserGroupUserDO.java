package com.zsh.cloud.system.infrastructure.persistence.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zsh.cloud.common.tenant.entity.TenantBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户组、用户绑定DO.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/26 11:05
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_user_group_user")
public class SysUserGroupUserDO extends TenantBaseDO {
    
    /**
     * 用户组ID.
     */
    private String groupId;
    
    /**
     * 用户ID.
     */
    private String userId;
}
