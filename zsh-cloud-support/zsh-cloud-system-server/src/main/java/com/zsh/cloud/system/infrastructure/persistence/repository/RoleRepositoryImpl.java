package com.zsh.cloud.system.infrastructure.persistence.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.cloud.common.mybatis.datascope.enums.DataScopeTypeEnum;
import com.zsh.cloud.system.domain.model.org.OrgId;
import com.zsh.cloud.system.domain.model.role.Role;
import com.zsh.cloud.system.domain.model.role.RoleCode;
import com.zsh.cloud.system.domain.model.role.RoleId;
import com.zsh.cloud.system.domain.model.role.RoleName;
import com.zsh.cloud.system.domain.model.role.RoleRepository;
import com.zsh.cloud.system.domain.model.user.UserId;
import com.zsh.cloud.system.domain.service.strategy.DataScopeContext;
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
import java.util.Set;
import java.util.stream.Collectors;

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
    
    @Autowired
    private DataScopeContext dataScopeContext;
    
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
    public List<Role> find(UserId userId) {
        List<SysRoleDO> sysRoleDOList = baseMapper.queryUserRole(userId.getId());
        List<Role> roles = new ArrayList<>();
        if (CollectionUtils.isEmpty(sysRoleDOList)) {
            return roles;
        }
        sysRoleDOList.forEach(sysRoleDO -> roles.add(RoleConverter.toRole(sysRoleDO)));
        return roles;
    }
    
    @Override
    public List<Role> find(List<RoleName> roleNames) {
        List<String> names = roleNames.stream().map(roleName -> roleName.getName()).collect(Collectors.toList());
        List<Role> roles = new ArrayList<>();
        List<SysRoleDO> sysRoleDOList = baseMapper.selectList(SysRoleDO::getRoleName, names);
        if (CollectionUtils.isEmpty(sysRoleDOList)) {
            return roles;
        }
        sysRoleDOList.forEach(sysRoleDO -> roles.add(RoleConverter.toRole(sysRoleDO)));
        return roles;
    }
    
    @Override
    public RoleId store(Role role) {
        SysRoleDO sysRoleDO = RoleConverter.fromRole(role);
        this.saveOrUpdate(sysRoleDO);
        RoleId roleId = new RoleId(sysRoleDO.getId());
        saveRoleOrg(role.getUserId(), roleId, role.getDsType(), role.getOrgIdList());
        return roleId;
    }
    
    @Override
    public void remove(List<RoleId> roleIds) {
        List<String> ids = new ArrayList<>();
        roleIds.forEach(roleId -> ids.add(roleId.getId()));
        this.removeByIds(ids);
        // 角色与组织关联
        sysRoleOrgMapper.deleteByRoleIds(ids);
    }
    
    /**
     * 保存 角色组织关系.
     *
     * @param userId
     * @param roleId
     * @param dsType
     * @param orgIdList
     */
    private void saveRoleOrg(UserId userId, RoleId roleId, DataScopeTypeEnum dsType, List<OrgId> orgIdList) {
        if (orgIdList == null && userId == null) {
            // 只有 禁用启用 才会进来
            return;
        }
        // 先删除角色与组织关系
        List<String> roleIds = new ArrayList<>();
        roleIds.add(roleId.getId());
        sysRoleOrgMapper.deleteByRoleIds(roleIds);
        // 根据 数据范围类型 和 勾选的组织ID， 重新计算全量的组织ID
        Set<OrgId> orgIds = dataScopeContext.getOrgIdsForDataScope(orgIdList, dsType, userId.getId());
        if (!CollectionUtils.isEmpty(orgIds)) {
            // 保存角色与组织关系
            for (OrgId orgId : orgIds) {
                SysRoleOrgDO sysRoleOrgDO = new SysRoleOrgDO();
                sysRoleOrgDO.setRoleId(roleId.getId());
                sysRoleOrgDO.setOrgId(orgId.getId());
                sysRoleOrgMapper.insert(sysRoleOrgDO);
            }
        }
    }
}
