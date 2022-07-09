package com.zsh.cloud.system.domain.model.org;

import com.zsh.cloud.system.application.model.dto.OrgDTO;

import java.util.List;

/**
 * 组织-Repository接口.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/20 13:45
 */
public interface OrgRepository {
    
    /**
     * 获取组织
     *
     * @param orgId
     * @return
     */
    Org find(OrgId orgId);
    
    /**
     * 获取组织.
     *
     * @param orgName
     * @return
     */
    List<Org> find(OrgName orgName);
    
    /**
     * 获取组织.
     *
     * @param parentId
     * @return
     */
    List<Org> queryList(OrgId parentId);
    
    /**
     * 保存.
     *
     * @param org
     */
    OrgId store(Org org);
    
    /**
     * 删除.
     *
     * @param orgIds
     */
    void remove(List<OrgId> orgIds);
    
    List<Org> findChildren(OrgId orgId);
}
