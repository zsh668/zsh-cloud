package com.zsh.cloud.system.application.assembler;

import com.zsh.cloud.system.api.dto.AuthenticationDTO;
import com.zsh.cloud.system.domain.model.user.User;

/**
 * Assembler class for the AuthenticationDTOAssembler.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/25 11:50
 */
public class AuthenticationDTOAssembler {
    
    /**
     * 转换对象.
     *
     * @param user
     * @return
     */
    public static AuthenticationDTO fromUser(final User user) {
        AuthenticationDTO authenticationDTO = new AuthenticationDTO();
        authenticationDTO.setUserId(user.getUserId().getId());
        authenticationDTO.setUserName(user.getUserName().getName());
        authenticationDTO.setPassword(user.getPassword().getPassword());
        authenticationDTO.setTenantId(user.getTenantId().getId());
        authenticationDTO.setStatus(user.getStatus().getCode());
        return authenticationDTO;
    }
}
