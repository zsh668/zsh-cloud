package com.zsh.cloud.system.infrastructure.persistence.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zsh.cloud.common.tenant.entity.TenantBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色组织关系DO.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/26 11:05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role_org")
public class SysRoleOrgDO extends TenantBaseDO {
    
    /**
     * 角色ID.
     */
    private String roleId;
    
    /**
     * 部门ID.
     */
    private String orgId;
}
