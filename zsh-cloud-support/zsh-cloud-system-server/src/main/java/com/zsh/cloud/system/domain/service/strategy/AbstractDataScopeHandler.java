package com.zsh.cloud.system.domain.service.strategy;

import com.zsh.cloud.system.domain.model.org.OrgId;

import java.util.List;
import java.util.Set;

/**
 * 创建抽象策略角色 主要作用 数据权限范围使用.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/8 17:45
 */

public interface AbstractDataScopeHandler {
    
    /**
     * 获取最新的全量的组织id列表.
     *
     * @param orgIdList 已选的组织id
     * @param userId    用户id
     * @return
     */
    Set<OrgId> getOrgIds(List<OrgId> orgIdList, String userId);
    
    /**
     * 获取该用户的组织ID列表（数据权限）
     *
     * @param roleId 最小权限（DataScopeTypeEnum code）值所属角色ID
     * @param userId 用户id
     * @return
     */
    Set<String> getOrgIds(String roleId, String userId);
}
