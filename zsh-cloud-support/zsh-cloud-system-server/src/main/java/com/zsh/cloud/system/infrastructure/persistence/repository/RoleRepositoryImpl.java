package com.zsh.cloud.system.infrastructure.persistence.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.cloud.system.domain.model.org.OrgId;
import com.zsh.cloud.system.domain.model.role.Role;
import com.zsh.cloud.system.domain.model.role.RoleCode;
import com.zsh.cloud.system.domain.model.role.RoleId;
import com.zsh.cloud.system.domain.model.role.RoleName;
import com.zsh.cloud.system.domain.model.role.RoleRepository;
import com.zsh.cloud.system.infrastructure.persistence.converter.RoleConverter;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysRoleDO;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysRoleOrgDO;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysRoleMapper;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysRoleOrgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

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
    
    @Autowired
    private SysRoleOrgMapper sysRoleOrgMapper;
    
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
    
    @Override
    public RoleId store(Role role) {
        SysRoleDO sysRoleDO = RoleConverter.fromRole(role);
        this.saveOrUpdate(sysRoleDO);
        String roleId = sysRoleDO.getId();
        // 先删除角色与组织关系
        List<String> roleIds = new ArrayList<>();
        roleIds.add(roleId);
        sysRoleOrgMapper.deleteByRoleIds(roleIds);
        List<OrgId> orgIds = role.getOrgIdList();
        if (!CollectionUtils.isEmpty(orgIds)) {
            // 保存角色与组织关系
            for (OrgId orgId : orgIds) {
                SysRoleOrgDO sysRoleOrgDO = new SysRoleOrgDO();
                sysRoleOrgDO.setRoleId(roleId);
                sysRoleOrgDO.setOrgId(orgId.getId());
                sysRoleOrgMapper.insert(sysRoleOrgDO);
            }
        }
        return new RoleId(roleId);
    }
    
    @Override
    public void remove(List<RoleId> roleIds) {
        List<String> ids = new ArrayList<>();
        roleIds.forEach(roleId -> {
            ids.add(roleId.getId());
        });
        this.removeByIds(ids);
        // 角色与组织关联
        sysRoleOrgMapper.deleteByRoleIds(ids);
    }
}
