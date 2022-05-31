package com.zsh.cloud.system.application;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.system.application.dto.RoleDTO;
import com.zsh.cloud.system.application.dto.RolePageDTO;
import com.zsh.cloud.system.application.query.RolePageQuery;

/**
 * 角色查询服务接口.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/31 10:46
 */
public interface RoleQueryService {
    
    /**
     * 分页查询
     *
     * @param rolePageQuery
     * @return
     */
    Page<RolePageDTO> queryPage(RolePageQuery rolePageQuery);
    
    /**
     * 通过ID获取
     *
     * @param id
     * @return
     */
    RoleDTO find(String id);
}
