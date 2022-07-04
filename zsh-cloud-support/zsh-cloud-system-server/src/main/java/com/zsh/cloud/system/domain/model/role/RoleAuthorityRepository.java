package com.zsh.cloud.system.domain.model.role;

import com.zsh.cloud.system.infrastructure.persistence.entity.SysRoleAuthorityDO;

import java.util.List;

/**
 * 角色资源-Repository接口.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/4 11:56
 */
public interface RoleAuthorityRepository {
    
    /**
     * 根据角色ID 删除.
     *
     * @param roleId
     */
    void deleteByRoleId(String roleId);
    
    /**
     * 批量保存
     * @param list
     */
    void storeBatch(List<SysRoleAuthorityDO> list);
}
