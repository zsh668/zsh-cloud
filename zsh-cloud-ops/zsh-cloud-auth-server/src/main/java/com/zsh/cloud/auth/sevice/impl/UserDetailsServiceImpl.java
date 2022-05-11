package com.zsh.cloud.auth.sevice.impl;

import com.zsh.cloud.auth.domain.User;
import com.zsh.cloud.system.api.dto.AuthenticationDTO;
import com.zsh.cloud.system.api.dubbo.AuthenticationDubboService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 自定义用户认证和授权.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/13 10:29
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @DubboReference
    protected AuthenticationDubboService authenticationDubboService;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthenticationDTO authenticationDTO = authenticationDubboService.loginByUserName(username);
        if (authenticationDTO != null) {
            return new User(authenticationDTO);
        } else {
            throw new UsernameNotFoundException("");
        }
    }
}
