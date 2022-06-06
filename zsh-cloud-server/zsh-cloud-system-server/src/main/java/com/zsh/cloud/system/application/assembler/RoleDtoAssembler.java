package com.zsh.cloud.system.application.assembler;

import com.zsh.cloud.common.core.domain.IDict;
import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.common.core.enums.BooleanEnum;
import com.zsh.cloud.common.core.enums.StatusEnum;
import com.zsh.cloud.common.mybatis.datascope.enums.DataScopeTypeEnum;
import com.zsh.cloud.system.application.command.RoleCreateCommand;
import com.zsh.cloud.system.application.command.RoleUpdateCommand;
import com.zsh.cloud.system.application.dto.RoleDTO;
import com.zsh.cloud.system.application.dto.RolePageDTO;
import com.zsh.cloud.system.domain.model.org.OrgId;
import com.zsh.cloud.system.domain.model.role.Role;
import com.zsh.cloud.system.domain.model.role.RoleCode;
import com.zsh.cloud.system.domain.model.role.RoleId;
import com.zsh.cloud.system.domain.model.role.RoleName;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysRoleDO;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;

import java.util.ArrayList;
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
        return Role.builder().roleCode(new RoleCode(roleCommand.getRoleCode()))
                .roleName(new RoleName(roleCommand.getRoleName())).repel(repel)
                .dsType(IDict.getByCode(DataScopeTypeEnum.class, roleCommand.getDsType())).readonly(BooleanEnum.FALSE)
                .orgIdList(orgIdList).status(StatusEnum.ENABLE).describe(roleCommand.getDescribe()).build();
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
        return Role.builder().roleId(new RoleId(roleCommand.getId())).roleCode(new RoleCode(roleCommand.getRoleCode()))
                .roleName(new RoleName(roleCommand.getRoleName())).repel(repel)
                .dsType(IDict.getByCode(DataScopeTypeEnum.class, roleCommand.getDsType())).readonly(BooleanEnum.FALSE)
                .orgIdList(orgIdList).status(StatusEnum.ENABLE).describe(roleCommand.getDescribe()).build();
    }
}
