package com.zsh.cloud.system.infrastructure.persistence.converter;

import com.zsh.cloud.common.core.domain.IDict;
import com.zsh.cloud.common.core.enums.BooleanEnum;
import com.zsh.cloud.common.core.enums.StatusEnum;
import com.zsh.cloud.common.core.exception.code.enums.ServiceErrorCode;
import com.zsh.cloud.common.core.util.Assert;
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
        return new Role(new RoleId(sysRoleDO.getId()), new RoleCode(sysRoleDO.getRoleCode()),
                new RoleName(sysRoleDO.getRoleName()), repel,
                IDict.getByCode(DataScopeTypeEnum.class, sysRoleDO.getDsType()), null,
                IDict.getByCode(BooleanEnum.class, sysRoleDO.getReadonly()),
                IDict.getByCode(StatusEnum.class, sysRoleDO.getStatus()), sysRoleDO.getDescribe());
    }
    
    /**
     * 转换.
     *
     * @param role
     * @return
     */
    public static SysRoleDO fromRole(Role role) {
        Assert.notNull(role, ServiceErrorCode.ROLE_NOT_EXISTS);
        SysRoleDO sysRoleDO = new SysRoleDO();
        sysRoleDO.setId(role.getRoleId() == null ? null : role.getRoleId().getId());
        sysRoleDO.setRoleCode(role.getRoleCode() == null ? null : role.getRoleCode().getCode());
        sysRoleDO.setRoleName(role.getRoleName() == null ? null : role.getRoleName().getName());
        sysRoleDO.setRepel(role.getRepel() == null ? null : role.getRepel().getId());
        sysRoleDO.setDsType(role.getDsType() == null ? null : role.getDsType().getCode());
        sysRoleDO.setReadonly(role.getReadonly() == null ? null : role.getReadonly().getCode());
        sysRoleDO.setDescribe(role.getDescribe());
        sysRoleDO.setStatus(role.getStatus() == null ? null : role.getStatus().getCode());
        return sysRoleDO;
    }
}
