package com.zsh.cloud.system.application.assembler;

import com.zsh.cloud.system.api.dto.LoginLogDTO;
import com.zsh.cloud.system.domain.model.log.login.LoginLog;
import com.zsh.cloud.system.domain.model.user.Account;
import org.mapstruct.Mapper;

/**
 * 登录日志Assembler.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/21 17:56
 */
@Mapper(componentModel = "spring")
public interface LoginLogDtoAssembler {
    
    /**
     * 转换.
     *
     * @param logDTO
     * @return
     */
    default LoginLog toLog(LoginLogDTO logDTO) {
        return new LoginLog(logDTO.getRequestIp(), new Account(logDTO.getAccount()), logDTO.getDescription(),
                logDTO.getLoginTime(), logDTO.getUa(), logDTO.getBrowser(), logDTO.getBrowserVersion(),
                logDTO.getOperatingSystem(), logDTO.getLocation());
    }
}
