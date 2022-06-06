package com.zsh.cloud.system.domain.specification;

import com.zsh.cloud.common.core.domain.specification.AbstractSpecification;
import com.zsh.cloud.common.core.exception.code.enums.ServiceErrorCode;
import com.zsh.cloud.common.core.util.Assert;
import com.zsh.cloud.system.domain.model.role.Role;
import com.zsh.cloud.system.domain.model.role.RoleRepository;

/**
 * 角色创建Specification.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/24 14:48
 */
public class RoleCreateSpecification extends AbstractSpecification<Role> {
    
    private RoleRepository roleRepository;
    
    public RoleCreateSpecification(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    
    @Override
    public boolean isSatisfiedBy(Role role) {
        if (role.getRoleName() != null) {
            Role existRole = roleRepository.find(role.getRoleName());
            Assert.notTrue(existRole != null && !existRole.getRoleId().sameValueAs(role.getRoleId()),
                    ServiceErrorCode.ROLE_NAME_EXISTS);
        }
        if (role.getRoleCode() != null) {
            Role existRole = roleRepository.find(role.getRoleCode());
            Assert.notTrue(existRole != null && !existRole.getRoleId().sameValueAs(role.getRoleId()),
                    ServiceErrorCode.ROLE_CODE_EXISTS);
        }
        return true;
    }
}
