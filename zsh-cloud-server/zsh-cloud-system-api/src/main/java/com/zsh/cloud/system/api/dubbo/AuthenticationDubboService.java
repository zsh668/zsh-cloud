package com.zsh.cloud.system.api.dubbo;

import com.zsh.cloud.system.api.dto.AuthenticationDTO;

/**
 * 认证服务 api.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/13 14:37
 */
public interface AuthenticationDubboService {
    
    AuthenticationDTO loginByUserName(String userName);
}
