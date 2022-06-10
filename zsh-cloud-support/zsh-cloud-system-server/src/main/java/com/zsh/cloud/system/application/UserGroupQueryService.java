package com.zsh.cloud.system.application;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.system.application.dto.UserGroupDTO;
import com.zsh.cloud.system.application.dto.UserGroupPageDTO;
import com.zsh.cloud.system.application.query.UserGroupPageQuery;

/**
 * 用户组查询服务接口.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/7 18:33
 */
public interface UserGroupQueryService {
    
    /**
     * 分页查询.
     *
     * @param query
     * @return
     */
    Page<UserGroupPageDTO> queryPage(UserGroupPageQuery query);
    
    /**
     * 通过用户组ID获取用户.
     *
     * @param userGroupId
     * @return
     */
    UserGroupDTO find(String userGroupId);
}
