package com.zsh.cloud.system.infrastructure.persistence.converter;

import com.zsh.cloud.common.core.domain.IDict;
import com.zsh.cloud.common.core.enums.BooleanEnum;
import com.zsh.cloud.common.core.enums.StatusEnum;
import com.zsh.cloud.common.mybatis.datascope.enums.DataScopeTypeEnum;
import com.zsh.cloud.system.domain.model.role.Role;
import com.zsh.cloud.system.domain.model.role.RoleCode;
import com.zsh.cloud.system.domain.model.role.RoleId;
import com.zsh.cloud.system.domain.model.role.RoleName;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysRoleDO;
import org.apache.commons.lang3.StringUtils;

/**
 * 角色Converter.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/31 17:24
 */
public class RoleConverter {
    
    /**
     * 转换.
     *
     * @param sysRoleDO
     * @return
     */
    public static Role toRole(SysRoleDO sysRoleDO) {
        if (sysRoleDO == null) {
            return null;
        }
        RoleId repel = StringUtils.isBlank(sysRoleDO.getRepel()) ? null : new RoleId(sysRoleDO.getRepel());
        return Role.builder().roleId(new RoleId(sysRoleDO.getId())).roleName(new RoleName(sysRoleDO.getRoleName()))
                .roleCode(new RoleCode(sysRoleDO.getRoleCode())).repel(repel)
                .dsType(IDict.getByCode(DataScopeTypeEnum.class, sysRoleDO.getDsType()))
                .readonly(IDict.getByCode(BooleanEnum.class, sysRoleDO.getReadonly()))
                .status(IDict.getByCode(StatusEnum.class, sysRoleDO.getStatus())).describe(sysRoleDO.getDescribe())
                .createdTime(sysRoleDO.getCreatedTime()).build();
    }
}
