package com.zsh.cloud.system.infrastructure.persistence.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zsh.cloud.common.tenant.entity.TenantBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色的资源DO.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/26 11:05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role_authority")
public class SysRoleAuthorityDO extends TenantBaseDO {
    
    /**
     * 角色ID.
     */
    private String roleId;
    
    /**
     * 资源id.
     */
    private String authorityId;
    
    /**
     * 权限类型 1:菜单;2:资源.
     */
    private Integer authorityType;
}
