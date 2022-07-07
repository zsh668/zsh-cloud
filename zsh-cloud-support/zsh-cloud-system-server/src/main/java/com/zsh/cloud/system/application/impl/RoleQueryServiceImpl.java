package com.zsh.cloud.system.application.impl;

import com.zsh.cloud.common.core.domain.IDict;
import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.system.application.RoleQueryService;
import com.zsh.cloud.system.application.assembler.RoleDtoAssembler;
import com.zsh.cloud.system.application.model.dto.RoleAuthorityDTO;
import com.zsh.cloud.system.application.model.dto.RoleDTO;
import com.zsh.cloud.system.application.model.dto.RolePageDTO;
import com.zsh.cloud.system.application.model.query.RolePageQuery;
import com.zsh.cloud.system.domain.model.menu.AuthorizeTypeEnum;
import com.zsh.cloud.system.domain.model.role.Role;
import com.zsh.cloud.system.domain.model.role.RoleId;
import com.zsh.cloud.system.domain.model.role.RoleRepository;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysRoleAuthorityDO;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysRoleDO;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysRoleAuthorityMapper;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色查询服务实现类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/31 12:18
 */
@Service
public class RoleQueryServiceImpl implements RoleQueryService {
    
    @Autowired
    private SysRoleMapper sysRoleMapper;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private RoleDtoAssembler roleDtoAssembler;
    
    @Autowired
    private SysRoleAuthorityMapper sysRoleAuthorityMapper;
    
    @Override
    public Page<RolePageDTO> queryPage(RolePageQuery query) {
        Page<SysRoleDO> page = sysRoleMapper.selectPage(query);
        return roleDtoAssembler.toDto(page);
    }
    
    @Override
    public List<RolePageDTO> queryList(RolePageQuery query) {
        List<SysRoleDO> list = sysRoleMapper.selectList(query);
        return roleDtoAssembler.toDto(list);
    }
    
    @Override
    public RoleDTO find(String id) {
        Role role = roleRepository.find(new RoleId(id));
        return roleDtoAssembler.fromRole(role);
    }
    
    @Override
    public RoleAuthorityDTO findRoleAuthority(Long roleId) {
        List<SysRoleAuthorityDO> list = sysRoleAuthorityMapper.selectList(SysRoleAuthorityDO::getRoleId, roleId);
        List<String> menuIds = list.stream().filter(item -> AuthorizeTypeEnum.MENU.sameValueAs(
                        IDict.getByCode(AuthorizeTypeEnum.class, item.getAuthorityType())))
                .map(SysRoleAuthorityDO::getAuthorityId).collect(Collectors.toList());
        List<String> resourceIds = list.stream().filter(item -> AuthorizeTypeEnum.RESOURCE.sameValueAs(
                        IDict.getByCode(AuthorizeTypeEnum.class, item.getAuthorityType())))
                .map(SysRoleAuthorityDO::getAuthorityId).collect(Collectors.toList());
        RoleAuthorityDTO authority = new RoleAuthorityDTO();
        authority.setMenuIds(menuIds).setResourceIds(resourceIds);
        return authority;
    }
}
