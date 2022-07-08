package com.zsh.cloud.system.infrastructure.persistence.converter;

import com.zsh.cloud.common.core.domain.IDict;
import com.zsh.cloud.common.core.enums.StatusEnum;
import com.zsh.cloud.common.core.exception.code.enums.ServiceErrorCode;
import com.zsh.cloud.common.core.util.Assert;
import com.zsh.cloud.system.domain.model.role.RoleId;
import com.zsh.cloud.system.domain.model.user.UserId;
import com.zsh.cloud.system.domain.model.user.UserName;
import com.zsh.cloud.system.domain.model.usergroup.UserGroup;
import com.zsh.cloud.system.domain.model.usergroup.UserGroupId;
import com.zsh.cloud.system.domain.model.usergroup.UserGroupName;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysUserGroupDO;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

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
     * @param userIds
     * @param userNames
     * @return
     */
    public static UserGroup toUserGroup(SysUserGroupDO userGroupDO, List<String> userIds, List<String> userNames) {
        if (userGroupDO == null) {
            return null;
        }
        List<UserId> userIdList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(userIds)) {
            userIds.forEach(userId -> {
                userIdList.add(new UserId(userId));
            });
        }
        List<UserName> userNameList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(userNames)) {
            userNames.forEach(userName -> {
                userNameList.add(new UserName(userName));
            });
        }
        return new UserGroup(new UserGroupId(userGroupDO.getId()), new UserGroupName(userGroupDO.getGroupName()),
                new RoleId(userGroupDO.getRoleId()), userGroupDO.getUserCount(),
                IDict.getByCode(StatusEnum.class, userGroupDO.getStatus()), userIdList, userNameList,
                userGroupDO.getDescribe());
    }
    
    /**
     * 转换.
     *
     * @param userGroup
     * @return
     */
    public static SysUserGroupDO fromUserGroup(UserGroup userGroup) {
        Assert.notNull(userGroup, ServiceErrorCode.USER_GROUP_NOT_EXISTS);
        SysUserGroupDO sysUserGroupDO = new SysUserGroupDO();
        sysUserGroupDO.setId(userGroup.getUserGroupId() == null ? null : userGroup.getUserGroupId().getId());
        sysUserGroupDO.setGroupName(
                userGroup.getUserGroupName() == null ? null : userGroup.getUserGroupName().getName());
        sysUserGroupDO.setUserCount(userGroup.getUserCount());
        sysUserGroupDO.setRoleId(userGroup.getRoleId() == null ? null : userGroup.getRoleId().getId());
        sysUserGroupDO.setStatus(userGroup.getStatus() == null ? null : userGroup.getStatus().getCode());
        sysUserGroupDO.setDescribe(userGroup.getDescribe());
        return sysUserGroupDO;
    }
}
