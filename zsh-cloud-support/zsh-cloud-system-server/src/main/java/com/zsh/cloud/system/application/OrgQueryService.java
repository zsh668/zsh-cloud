package com.zsh.cloud.system.application;

import com.zsh.cloud.system.application.model.dto.OrgDTO;
import com.zsh.cloud.system.application.model.dto.OrgTreeDTO;
import com.zsh.cloud.system.application.model.query.OrgPageQuery;

import java.util.List;

/**
 * 组织查询服务接口.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/14 14:22
 */
public interface OrgQueryService {
    
    /**
     * 查询.
     *
     * @param orgPageQuery
     * @return
     */
    List<OrgTreeDTO> queryList(OrgPageQuery orgPageQuery);
    
    /**
     * 根据ID查询信息.
     *
     * @param id
     * @return
     */
    OrgDTO find(String id);
    
    /**
     * 获取所有组织.
     *
     * @return
     */
    List<OrgDTO> findAll();
    
    /**
     * 根据ID获取所有孩子.
     *
     * @param orgId
     * @return
     */
    List<OrgDTO> findChildren(String orgId);
}
