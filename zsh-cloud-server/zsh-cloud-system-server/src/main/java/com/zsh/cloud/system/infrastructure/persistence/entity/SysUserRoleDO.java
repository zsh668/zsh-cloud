package com.zsh.cloud.system.infrastructure.persistence.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zsh.cloud.common.tenant.entity.TenantBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户角色DO.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/26 11:05
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_user_role")
public class SysUserRoleDO extends TenantBaseDO {
    
    /**
     * 用户ID.
     */
    private String userId;
    
    /**
     * 角色ID.
     */
    private String roleId;
}
