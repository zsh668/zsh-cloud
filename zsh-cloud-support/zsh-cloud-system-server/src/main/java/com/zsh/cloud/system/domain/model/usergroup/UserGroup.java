package com.zsh.cloud.system.domain.model.usergroup;

import com.zsh.cloud.common.core.domain.Entity;
import com.zsh.cloud.common.core.enums.StatusEnum;
import com.zsh.cloud.system.domain.model.role.RoleId;
import com.zsh.cloud.system.domain.model.user.UserId;
import com.zsh.cloud.system.domain.model.user.UserName;
import lombok.Getter;

import java.util.List;

/**
 * 用户组.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/25 11:53
 */
@Getter
public class UserGroup implements Entity<UserGroup> {
    
    /**
     * 用户组ID.
     */
    private UserGroupId userGroupId;
    
    /**
     * 用户组名称.
     */
    private UserGroupName userGroupName;
    
    /**
     * 角色Id.
     */
    private RoleId roleId;
    
    /**
     * 用户数量.
     */
    private Integer userCount;
    
    /**
     * 状态.
     */
    private StatusEnum status;
    
    /**
     * 功能描述.
     */
    private String describe;
    
    /**
     * 用户id集合
     */
    private List<UserId> userIds;
    
    /**
     * 姓名集合
     */
    private List<UserName> userNames;
    
    public UserGroup(UserGroupName userGroupName, RoleId roleId, Integer userCount, List<UserId> userIds,
            String describe) {
        this.userGroupName = userGroupName;
        this.roleId = roleId;
        this.status = StatusEnum.ENABLE;
        this.userCount = userCount;
        this.userIds = userIds;
        this.describe = describe;
    }
    
    public UserGroup(UserGroupId userGroupId, UserGroupName userGroupName, RoleId roleId, Integer userCount,
            StatusEnum status, List<UserId> userIds, List<UserName> userNames, String describe) {
        this.userGroupId = userGroupId;
        this.userGroupName = userGroupName;
        this.roleId = roleId;
        this.userCount = userCount;
        this.status = status;
        this.userIds = userIds;
        this.userNames = userNames;
        this.describe = describe;
    }
    
    /**
     * 是否有效.
     *
     * @return
     */
    public boolean isEnable() {
        return status == StatusEnum.ENABLE;
    }
    
    /**
     * 启用、禁用.
     */
    public void disable() {
        this.status = isEnable() ? StatusEnum.DISABLE : StatusEnum.ENABLE;
    }
    
    @Override
    public boolean sameIdentityAs(UserGroup other) {
        return other != null && userGroupId.sameValueAs(other.userGroupId);
    }
}
