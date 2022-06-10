package com.zsh.cloud.system.domain.model.usergroup;

import java.util.List;

/**
 * 用户组-Repository接口.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/20 13:45
 */
public interface UserGroupRepository {
    
    /**
     * 通过用户组ID获取用户组.
     *
     * @param userGroupId
     * @return
     */
    UserGroup find(UserGroupId userGroupId);
    
    /**
     * 通过用户组名称获取用户组.
     *
     * @param userGroupName
     * @return
     */
    UserGroup find(UserGroupName userGroupName);
    
    /**
     * 保存.
     *
     * @param userGroup
     * @return
     */
    UserGroupId store(UserGroup userGroup);
    
    /**
     * 删除.
     *
     * @param userGroupIds
     */
    void remove(List<UserGroupId> userGroupIds);
}
