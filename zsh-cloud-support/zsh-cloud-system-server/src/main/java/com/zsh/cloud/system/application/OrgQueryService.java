package com.zsh.cloud.system.application;

import com.zsh.cloud.system.application.dto.OrgDTO;
import com.zsh.cloud.system.application.dto.OrgTreeDTO;
import com.zsh.cloud.system.application.query.OrgPageQuery;

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
}
