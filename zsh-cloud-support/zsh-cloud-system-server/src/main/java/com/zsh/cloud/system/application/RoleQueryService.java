package com.zsh.cloud.system.application;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.system.application.model.dto.RoleAuthorityDTO;
import com.zsh.cloud.system.application.model.dto.RoleDTO;
import com.zsh.cloud.system.application.model.dto.RolePageDTO;
import com.zsh.cloud.system.application.model.query.RolePageQuery;

import java.util.List;

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
     * 查询
     *
     * @param rolePageQuery
     * @return
     */
    List<RolePageDTO> queryList(RolePageQuery rolePageQuery);
    
    /**
     * 通过ID获取
     *
     * @param id
     * @return
     */
    RoleDTO find(String id);
    
    /**
     * 查询角色资源.
     *
     * @param roleId
     * @return
     */
    RoleAuthorityDTO findRoleAuthority(Long roleId);
}
