package com.zsh.cloud.system.domain.model.role;

import com.zsh.cloud.system.domain.model.user.UserId;

import java.util.List;

/**
 * 角色-Repository接口.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/20 13:45
 */
public interface RoleRepository {
    
    /**
     * 获取角色
     *
     * @param roleId
     * @return
     */
    Role find(RoleId roleId);
    
    /**
     * 获取角色
     *
     * @param roleName
     * @return
     */
    Role find(RoleName roleName);
    
    /**
     * 获取角色
     *
     * @param roleCode
     * @return
     */
    Role find(RoleCode roleCode);
    
    /**
     * 根据用户ID查询角色.
     *
     * @param userId
     * @return
     */
    List<Role> find(UserId userId);
    
    /**
     * 保存.
     *
     * @param role
     */
    RoleId store(Role role);
    
    /**
     * 删除.
     *
     * @param roleIds
     */
    void remove(List<RoleId> roleIds);
}
