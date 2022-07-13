package com.zsh.cloud.system.domain.model.userrole;

import com.zsh.cloud.system.domain.model.user.UserId;

import java.util.List;

/**
 * 用户组用户-Repository接口.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/24 18:30
 */
public interface UserRoleRepository {
    
    /**
     * 保存.
     *
     * @param userRole
     * @return
     */
    void store(UserRole userRole);
    
    /**
     * 删除.
     *
     * @param userIds
     */
    void remove(List<UserId> userIds);
}
