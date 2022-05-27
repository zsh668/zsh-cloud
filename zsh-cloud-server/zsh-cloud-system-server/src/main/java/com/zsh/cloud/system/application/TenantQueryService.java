package com.zsh.cloud.system.application;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.system.application.dto.TenantDTO;
import com.zsh.cloud.system.application.dto.TenantPageDTO;
import com.zsh.cloud.system.application.query.TenantPageQuery;

/**
 * 租户查询服务接口.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/29 14:44
 */
public interface TenantQueryService {
    
    /**
     * 分页查询.
     *
     * @param query
     * @return
     */
    Page<TenantPageDTO> queryPage(TenantPageQuery query);
    
    /**
     * 通过用户ID获取用户.
     *
     * @param userId
     * @return
     */
    TenantDTO find(String userId);
}
