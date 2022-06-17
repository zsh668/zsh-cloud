package com.zsh.cloud.system.application;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.system.application.dto.ResourceDTO;
import com.zsh.cloud.system.application.dto.ResourcePageDTO;
import com.zsh.cloud.system.application.query.ResourcePageQuery;

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
     * 根据ID查询.
     *
     * @param id
     * @return
     */
    ResourceDTO find(String id);
}
