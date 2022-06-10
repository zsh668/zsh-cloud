package com.zsh.cloud.system.domain.specification;

import com.zsh.cloud.common.core.domain.specification.AbstractSpecification;
import com.zsh.cloud.common.core.exception.code.enums.ServiceErrorCode;
import com.zsh.cloud.common.core.util.ServiceAssert;
import com.zsh.cloud.system.domain.model.role.Role;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysUserRoleDO;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysUserRoleMapper;

/**
 * 角色删除Specification.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/24 14:48
 */
public class RoleDeleteSpecification extends AbstractSpecification<Role> {
    
    private SysUserRoleMapper sysUserRoleMapper;
    
    public RoleDeleteSpecification(SysUserRoleMapper sysUserRoleMapper) {
        this.sysUserRoleMapper = sysUserRoleMapper;
    }
    
    @Override
    public boolean isSatisfiedBy(Role role) {
        Long count = sysUserRoleMapper.selectCount(SysUserRoleDO::getRoleId, role.getRoleId().getId());
        ServiceAssert.notTrue(count > 0, ServiceErrorCode.ROLE_VERIFICATION_ERROR.getCode(), "角色关联用户无法删除或禁用");
        return true;
    }
}
