package com.zsh.cloud.system.application.impl;

import com.zsh.cloud.common.core.exception.code.enums.ServiceErrorCode;
import com.zsh.cloud.common.core.util.Assert;
import com.zsh.cloud.system.application.RoleApplicationService;
import com.zsh.cloud.system.application.assembler.RoleDtoAssembler;
import com.zsh.cloud.system.application.command.RoleCreateCommand;
import com.zsh.cloud.system.application.command.RoleUpdateCommand;
import com.zsh.cloud.system.domain.model.role.Role;
import com.zsh.cloud.system.domain.model.role.RoleId;
import com.zsh.cloud.system.domain.model.role.RoleRepository;
import com.zsh.cloud.system.domain.specification.RoleCreateSpecification;
import com.zsh.cloud.system.domain.specification.RoleDeleteSpecification;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色应用服务实现类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/31 12:18
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleApplicationServiceImpl implements RoleApplicationService {
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    
    @Autowired
    private RoleDtoAssembler roleDtoAssembler;
    
    @Override
    public void save(RoleCreateCommand roleCommand) {
        Role role = roleDtoAssembler.toRole(roleCommand);
        RoleCreateSpecification specification = new RoleCreateSpecification(roleRepository);
        specification.isSatisfiedBy(role);
        roleRepository.store(role);
    }
    
    @Override
    public void update(RoleUpdateCommand roleCommand) {
        Role role = roleDtoAssembler.toRole(roleCommand);
        RoleCreateSpecification specification = new RoleCreateSpecification(roleRepository);
        specification.isSatisfiedBy(role);
        roleRepository.store(role);
    }
    
    @Override
    public void deleteBatch(List<String> ids) {
        List<RoleId> roleIds = new ArrayList<>();
        RoleDeleteSpecification specification = new RoleDeleteSpecification(sysUserRoleMapper);
        ids.forEach(id -> {
            RoleId roleId = new RoleId(id);
            Role role = roleRepository.find(roleId);
            Assert.notTrue(role.isReadonly(), ServiceErrorCode.ROLE_PLATFORM_DELETE);
            specification.isSatisfiedBy(role);
            roleIds.add(roleId);
        });
        roleRepository.remove(roleIds);
    }
    
    @Override
    public void disable(String id) {
        Role role = roleRepository.find(new RoleId(id));
        Assert.notTrue(role.isReadonly(), ServiceErrorCode.ROLE_PLATFORM_DELETE);
        RoleDeleteSpecification specification = new RoleDeleteSpecification(sysUserRoleMapper);
        specification.isSatisfiedBy(role);
        role.disable();
        roleRepository.store(role);
    }
}
