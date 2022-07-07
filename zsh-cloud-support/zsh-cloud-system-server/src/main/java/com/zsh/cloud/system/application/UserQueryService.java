package com.zsh.cloud.system.application;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.common.mybatis.datascope.domain.DataPermission;
import com.zsh.cloud.system.application.model.dto.LoginDTO;
import com.zsh.cloud.system.application.model.dto.UserDTO;
import com.zsh.cloud.system.application.model.dto.UserPageDTO;
import com.zsh.cloud.system.application.model.query.UserPageQuery;

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
    Page<UserPageDTO> queryPage(UserPageQuery query);
    
    /**
     * 通过用户ID获取用户.
     *
     * @param userId
     * @return
     */
    UserDTO find(String userId);
    
    /**
     * 通过用户ID获取数据权限.
     *
     * @param userId
     * @return
     */
    DataPermission getDataScopeById(String userId);
    
    /**
     * 获取用户信息.
     *
     * @return
     */
    LoginDTO current();
}
