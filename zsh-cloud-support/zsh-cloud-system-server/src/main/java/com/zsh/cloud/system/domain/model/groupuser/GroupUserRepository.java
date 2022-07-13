package com.zsh.cloud.system.domain.model.groupuser;

import com.zsh.cloud.system.domain.model.user.UserId;

import java.util.List;

/**
 * 用户角色-Repository接口.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/24 18:30
 */
public interface GroupUserRepository {
    
    /**
     * 保存.
     *
     * @param groupUser
     * @return
     */
    void store(GroupUser groupUser);
    
    /**
     * 删除.
     *
     * @param userIds
     */
    void remove(List<UserId> userIds);
}
