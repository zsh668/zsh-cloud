package com.zsh.cloud.system.application.assembler;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.system.application.model.command.RoleCreateCommand;
import com.zsh.cloud.system.application.model.command.RoleUpdateCommand;
import com.zsh.cloud.system.application.model.dto.RoleDTO;
import com.zsh.cloud.system.application.model.dto.RolePageDTO;
import com.zsh.cloud.system.domain.model.org.OrgId;
import com.zsh.cloud.system.domain.model.role.Role;
import com.zsh.cloud.system.domain.model.role.RoleCode;
import com.zsh.cloud.system.domain.model.role.RoleId;
import com.zsh.cloud.system.domain.model.role.RoleName;
import com.zsh.cloud.system.domain.model.user.UserId;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysRoleDO;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysRoleOrgDO;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
     * @param roleOrgS
     * @return
     */
    default RoleDTO fromRole(Role role, List<SysRoleOrgDO> roleOrgS) {
        if (role == null) {
            return null;
        }
        RoleDTO roleDto = new RoleDTO();
        roleDto.setId(role.getRoleId() == null ? "" : role.getRoleId().getId())
                .setRoleCode(role.getRoleCode() == null ? "" : role.getRoleCode().getCode())
                .setRoleName(role.getRoleName() == null ? "" : role.getRoleName().getName())
                .setRepel(role.getRepel() == null ? "" : role.getRepel().getId())
                .setDsType(role.getDsType() == null ? null : role.getDsType())
                .setStatus(role.getStatus() == null ? null : role.getStatus().getCode())
                .setDescribe(role.getDescribe());
        if (roleOrgS != null) {
            List<String> orgIds = roleOrgS.stream().map(SysRoleOrgDO::getOrgId).collect(Collectors.toList());
            roleDto.setOrgList(orgIds);
        }
        return roleDto;
    }
    
    /**
     * 转换.
     *
     * @param roleCommand
     * @return
     */
    default Role toRole(RoleCreateCommand roleCommand) {
        List<OrgId> orgIdList = new ArrayList<>();
        if (roleCommand.getOrgList() != null) {
            roleCommand.getOrgList().forEach(orgId -> {
                orgIdList.add(new OrgId(orgId));
            });
        }
        RoleId repel = StringUtils.isBlank(roleCommand.getRepel()) ? null : new RoleId(roleCommand.getRepel());
        return new Role(new RoleCode(roleCommand.getRoleCode()), new RoleName(roleCommand.getRoleName()), repel,
                roleCommand.getDsType(), orgIdList, roleCommand.getDescribe(), new UserId(roleCommand.getUserId()));
    }
    
    /**
     * 转换.
     *
     * @param roleCommand
     * @return
     */
    default Role toRole(RoleUpdateCommand roleCommand) {
        List<OrgId> orgIdList = new ArrayList<>();
        if (roleCommand.getOrgList() != null) {
            roleCommand.getOrgList().forEach(orgId -> {
                orgIdList.add(new OrgId(orgId));
            });
        }
        RoleId repel = StringUtils.isBlank(roleCommand.getRepel()) ? null : new RoleId(roleCommand.getRepel());
        return new Role(new RoleId(roleCommand.getId()), new RoleCode(roleCommand.getRoleCode()),
                new RoleName(roleCommand.getRoleName()), repel, roleCommand.getDsType(), orgIdList, null, null,
                roleCommand.getDescribe(), new UserId(roleCommand.getUserId()));
    }
}
