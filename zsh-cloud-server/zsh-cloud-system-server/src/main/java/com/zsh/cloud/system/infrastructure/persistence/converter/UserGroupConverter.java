package com.zsh.cloud.system.infrastructure.persistence.converter;

import com.zsh.cloud.common.core.domain.IDict;
import com.zsh.cloud.common.core.enums.StatusEnum;
import com.zsh.cloud.common.core.exception.code.enums.ServiceErrorCode;
import com.zsh.cloud.common.core.util.Assert;
import com.zsh.cloud.system.domain.model.role.RoleId;
import com.zsh.cloud.system.domain.model.usergroup.UserGroup;
import com.zsh.cloud.system.domain.model.usergroup.UserGroupId;
import com.zsh.cloud.system.domain.model.usergroup.UserGroupName;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysUserGroupDO;

/**
 * 用户组Converter.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/10 10:58
 */
public class UserGroupConverter {
    
    /**
     * 转换.
     *
     * @param userGroupDO
     * @return
     */
    public static UserGroup toUserGroup(SysUserGroupDO userGroupDO) {
        if (userGroupDO == null) {
            return null;
        }
        return new UserGroup(new UserGroupId(userGroupDO.getId()), new UserGroupName(userGroupDO.getGroupName()),
                new RoleId(userGroupDO.getRoleId()), userGroupDO.getUserCount(),
                IDict.getByCode(StatusEnum.class, userGroupDO.getStatus()), null, userGroupDO.getDescribe());
    }
    
    public static SysUserGroupDO fromUserGroup(UserGroup userGroup) {
        Assert.notNull(userGroup, ServiceErrorCode.USER_GROUP_NOT_EXISTS);
        return null;
    }
}
