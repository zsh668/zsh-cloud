package com.zsh.cloud.system.domain.model.groupuser;

import com.zsh.cloud.common.core.domain.Entity;
import com.zsh.cloud.system.domain.model.user.UserId;
import com.zsh.cloud.system.domain.model.usergroup.UserGroupId;
import lombok.Getter;

import java.util.List;

/**
 * 用户组用户关系.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/25 11:53
 */
@Getter
public class GroupUser implements Entity<GroupUser> {
    
    /**
     * 用户组ID.
     */
    private UserGroupId userGroupId;
    
    /**
     * 用户id集合
     */
    private List<UserId> userIds;
    
    public GroupUser(UserGroupId userGroupId, List<UserId> userIds) {
        this.userGroupId = userGroupId;
        this.userIds = userIds;
    }
    
    @Override
    public boolean sameIdentityAs(GroupUser other) {
        return other != null && userGroupId.sameValueAs(other.userGroupId);
    }
}
