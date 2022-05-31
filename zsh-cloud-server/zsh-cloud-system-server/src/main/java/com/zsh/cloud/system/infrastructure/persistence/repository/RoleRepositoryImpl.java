package com.zsh.cloud.system.infrastructure.persistence.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.cloud.system.domain.model.role.Role;
import com.zsh.cloud.system.domain.model.role.RoleCode;
import com.zsh.cloud.system.domain.model.role.RoleId;
import com.zsh.cloud.system.domain.model.role.RoleName;
import com.zsh.cloud.system.domain.model.role.RoleRepository;
import com.zsh.cloud.system.infrastructure.persistence.converter.RoleConverter;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysRoleDO;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysRoleMapper;
import org.springframework.stereotype.Repository;

/**
 * 角色-Repository实现类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/20 13:44
 */
@Repository
public class RoleRepositoryImpl extends ServiceImpl<SysRoleMapper, SysRoleDO>
        implements RoleRepository, IService<SysRoleDO> {
    
    @Override
    public Role find(RoleId roleId) {
        SysRoleDO sysRoleDO = baseMapper.selectById(roleId.getId());
        return RoleConverter.toRole(sysRoleDO);
    }
    
    @Override
    public Role find(RoleName roleName) {
        SysRoleDO sysRoleDO = baseMapper.selectOne(SysRoleDO::getRoleName, roleName.getName());
        return RoleConverter.toRole(sysRoleDO);
    }
    
    @Override
    public Role find(RoleCode roleCode) {
        SysRoleDO sysRoleDO = baseMapper.selectOne(SysRoleDO::getRoleCode, roleCode.getCode());
        return RoleConverter.toRole(sysRoleDO);
    }
}
