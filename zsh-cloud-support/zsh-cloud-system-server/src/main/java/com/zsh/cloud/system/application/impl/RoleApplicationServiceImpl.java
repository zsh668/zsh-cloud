package com.zsh.cloud.system.application.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.zsh.cloud.common.core.exception.code.enums.ServiceErrorCode;
import com.zsh.cloud.common.core.util.Assert;
import com.zsh.cloud.common.core.util.CollectionUtils;
import com.zsh.cloud.system.application.RoleApplicationService;
import com.zsh.cloud.system.application.assembler.RoleDtoAssembler;
import com.zsh.cloud.system.application.command.RoleAuthorityCommand;
import com.zsh.cloud.system.application.command.RoleCreateCommand;
import com.zsh.cloud.system.application.command.RoleUpdateCommand;
import com.zsh.cloud.system.domain.model.menu.AuthorizeTypeEnum;
import com.zsh.cloud.system.domain.model.role.Role;
import com.zsh.cloud.system.domain.model.role.RoleAuthorityRepository;
import com.zsh.cloud.system.domain.model.role.RoleId;
import com.zsh.cloud.system.domain.model.role.RoleRepository;
import com.zsh.cloud.system.domain.specification.RoleCreateSpecification;
import com.zsh.cloud.system.domain.specification.RoleDeleteSpecification;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysRoleAuthorityDO;
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
    
    @Autowired
    private RoleAuthorityRepository roleAuthorityRepository;
    
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
    
    @Override
    public void saveRoleAuthority(RoleAuthorityCommand roleCommand) {
        // 删除
        roleAuthorityRepository.deleteByRoleId(roleCommand.getRoleId());
        // 保存
        List<SysRoleAuthorityDO> list = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(roleCommand.getResourceIds())) {
            //保存授予的资源
            List<SysRoleAuthorityDO> resourceList = CollectionUtils.convertList(roleCommand.getResourceIds(),
                    resourceId -> {
                        SysRoleAuthorityDO authorityDO = new SysRoleAuthorityDO();
                        authorityDO.setRoleId(roleCommand.getRoleId());
                        authorityDO.setAuthorityId(resourceId);
                        authorityDO.setAuthorityType(AuthorizeTypeEnum.RESOURCE.getCode());
                        return authorityDO;
                    });
            list.addAll(resourceList);
        }
        if (CollectionUtil.isNotEmpty(roleCommand.getMenuIds())) {
            //保存授予的菜单
            List<SysRoleAuthorityDO> menuList = CollectionUtils.convertList(roleCommand.getMenuIds(), menuId -> {
                SysRoleAuthorityDO authorityDO = new SysRoleAuthorityDO();
                authorityDO.setRoleId(roleCommand.getRoleId());
                authorityDO.setAuthorityId(menuId);
                authorityDO.setAuthorityType(AuthorizeTypeEnum.MENU.getCode());
                return authorityDO;
            });
            list.addAll(menuList);
        }
        roleAuthorityRepository.storeBatch(list);
    }
}
