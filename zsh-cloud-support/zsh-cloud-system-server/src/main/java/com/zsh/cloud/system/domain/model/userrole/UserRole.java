package com.zsh.cloud.system.domain.model.userrole;

import com.zsh.cloud.common.core.domain.Entity;
import com.zsh.cloud.system.domain.model.role.RoleId;
import com.zsh.cloud.system.domain.model.user.UserId;
import lombok.Getter;

import java.util.List;

/**
 * 用户角色.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/12 13:33
 */
@Getter
public class UserRole implements Entity<UserRole> {
    
    /**
     * 用户ID.
     */
    private UserId userId;
    
    /**
     * 角色Id列表.
     */
    private List<RoleId> roleIds;
    
    public UserRole(UserId userId, List<RoleId> roleIds) {
        this.userId = userId;
        this.roleIds = roleIds;
    }
    
    @Override
    public boolean sameIdentityAs(UserRole other) {
        return other != null && userId.sameValueAs(other.userId);
    }
}
