package com.zsh.cloud.system.application.assembler;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.system.application.dto.RoleDTO;
import com.zsh.cloud.system.application.dto.RolePageDTO;
import com.zsh.cloud.system.domain.model.role.Role;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysRoleDO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 角色Assembler.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/31 12:45
 */
@Mapper(componentModel = "spring")
public interface RoleDtoAssembler {
    
    /**
     * role转换.
     *
     * @param role
     * @return
     */
    RolePageDTO toDto(SysRoleDO role);
    
    /**
     * role转换.(RolePageDTO toDto())
     *
     * @param role
     * @return
     */
    List<RolePageDTO> toDto(List<SysRoleDO> role);
    
    /**
     * role转换.(List toDto())
     *
     * @param role
     * @return
     */
    Page<RolePageDTO> toDto(Page<SysRoleDO> role);
    
    /**
     * 转换.
     *
     * @param role
     * @return
     */
    default RoleDTO fromRole(Role role) {
        if (role == null) {
            return null;
        }
        RoleDTO roleDto = new RoleDTO();
        roleDto.setId(role.getRoleId() == null ? "" : role.getRoleId().getId());
        roleDto.setRoleCode(role.getRoleCode() == null ? "" : role.getRoleCode().getCode());
        roleDto.setRoleName(role.getRoleName() == null ? "" : role.getRoleName().getName());
        roleDto.setRepel(role.getRepel() == null ? "" : role.getRepel().getId());
        roleDto.setDsType(role.getDsType() == null ? null : role.getDsType().getCode());
        roleDto.setReadonly(role.getReadonly() == null ? null : role.getReadonly().getCode());
        roleDto.setStatus(role.getStatus() == null ? null : role.getStatus().getCode());
        roleDto.setDescribe(role.getDescribe());
        roleDto.setCreatedTime(role.getCreatedTime());
        return roleDto;
    }
}
