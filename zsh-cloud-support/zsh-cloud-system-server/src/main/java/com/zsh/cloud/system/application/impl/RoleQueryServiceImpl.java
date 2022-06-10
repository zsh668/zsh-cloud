package com.zsh.cloud.system.application.impl;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.system.application.RoleQueryService;
import com.zsh.cloud.system.application.assembler.RoleDtoAssembler;
import com.zsh.cloud.system.application.dto.RoleDTO;
import com.zsh.cloud.system.application.dto.RolePageDTO;
import com.zsh.cloud.system.application.query.RolePageQuery;
import com.zsh.cloud.system.domain.model.role.Role;
import com.zsh.cloud.system.domain.model.role.RoleId;
import com.zsh.cloud.system.domain.model.role.RoleRepository;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysRoleDO;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    
    @Override
    public Page<RolePageDTO> queryPage(RolePageQuery query) {
        Page<SysRoleDO> page = sysRoleMapper.selectPage(query);
        return roleDtoAssembler.toDto(page);
    }
    
    @Override
    public RoleDTO find(String id) {
        Role role = roleRepository.find(new RoleId(id));
        return roleDtoAssembler.fromRole(role);
    }
}
