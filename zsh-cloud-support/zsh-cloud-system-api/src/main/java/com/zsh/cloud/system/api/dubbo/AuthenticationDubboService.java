package com.zsh.cloud.system.api.dubbo;

import com.zsh.cloud.system.api.dto.AuthenticationDTO;

/**
 * 认证服务接口.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/13 14:37
 */
public interface AuthenticationDubboService {
    
    /**
     * 认证
     *
     * @param userName 用户名
     * @return
     */
    AuthenticationDTO loginByUserName(String userName);
}
