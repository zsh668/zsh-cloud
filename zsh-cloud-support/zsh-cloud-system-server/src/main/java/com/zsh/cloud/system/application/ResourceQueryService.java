package com.zsh.cloud.system.application;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.system.application.model.dto.ResourceDTO;
import com.zsh.cloud.system.application.model.dto.ResourcePageDTO;
import com.zsh.cloud.system.application.model.query.ResourcePageQuery;

import java.util.List;
import java.util.Set;

/**
 * 资源查询服务接口.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/29 11:03
 */
public interface ResourceQueryService {
    
    /**
     * 获取权限编码
     *
     * @param userId
     * @return
     */
    Set<String> getPermissionCodes(String userId);
    
    /**
     * 分页查询.
     *
     * @param resourcePageQuery
     * @return
     */
    Page<ResourcePageDTO> queryPage(ResourcePageQuery resourcePageQuery);
    
    /**
     * 根据菜单ID查询.
     *
     * @param menuId
     * @return
     */
    List<ResourceDTO> queryList(String menuId);
    
    /**
     * 根据ID查询.
     *
     * @param id
     * @return
     */
    ResourceDTO find(String id);
}
