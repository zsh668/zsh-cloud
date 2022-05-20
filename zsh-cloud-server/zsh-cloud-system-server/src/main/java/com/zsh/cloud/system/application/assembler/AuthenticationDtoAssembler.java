package com.zsh.cloud.system.application.assembler;

import com.zsh.cloud.system.api.dto.AuthenticationDTO;
import com.zsh.cloud.system.domain.model.user.User;
import org.mapstruct.Mapper;

/**
 * Assembler class for the AuthenticationDtoAssembler.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/25 11:50
 */
@Mapper(componentModel = "spring")
public interface AuthenticationDtoAssembler {
    
    /**
     * 转换对象.
     *
     * @param user
     * @return
     */
    default AuthenticationDTO fromUser(final User user) {
        AuthenticationDTO authentication = new AuthenticationDTO();
        authentication.setUserId(user.getUserId().getId());
        authentication.setUserName(user.getUserName().getName());
        authentication.setPassword(user.getPassword().getPassword());
        authentication.setTenantId(user.getTenantId().getId());
        authentication.setStatus(user.getStatus().getCode());
        return authentication;
    }
}
