package com.zsh.cloud.system.application;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.common.mybatis.datascope.domain.DataPermission;
import com.zsh.cloud.system.application.dto.UserDTO;
import com.zsh.cloud.system.application.query.UserPageQuery;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysUserDO;

/**
 * 用户查询服务接口.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/29 14:44
 */
public interface UserQueryService {
    
    /**
     * 分页查询.
     *
     * @param query
     * @return
     */
    Page<SysUserDO> queryPage(UserPageQuery query);
    
    /**
     * 通过用户ID获取用户.
     *
     * @param userId
     * @return
     */
    UserDTO find(String userId);
    
    DataPermission getDataScopeById(String userId);
}
