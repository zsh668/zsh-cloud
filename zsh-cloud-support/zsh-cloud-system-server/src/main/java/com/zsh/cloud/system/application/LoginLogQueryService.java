package com.zsh.cloud.system.application;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.system.application.model.dto.LoginLogPageDTO;
import com.zsh.cloud.system.application.model.query.LoginLogPageQuery;

/**
 * 登录日志查询服务接口.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/7 10:11
 */
public interface LoginLogQueryService {
    
    /**
     * 分页查询.
     *
     * @param loginLogPageQuery
     * @return
     */
    Page<LoginLogPageDTO> queryPage(LoginLogPageQuery loginLogPageQuery);
    
    /**
     * 根据ID查询.
     *
     * @param id
     * @return
     */
    LoginLogPageDTO find(String id);
}
