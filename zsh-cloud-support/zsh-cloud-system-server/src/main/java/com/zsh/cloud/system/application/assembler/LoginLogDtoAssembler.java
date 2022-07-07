package com.zsh.cloud.system.application.assembler;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.common.core.util.BeanUtils;
import com.zsh.cloud.system.api.dto.LoginLogDTO;
import com.zsh.cloud.system.application.model.dto.LoginLogPageDTO;
import com.zsh.cloud.system.domain.model.log.login.LoginLog;
import com.zsh.cloud.system.domain.model.user.Account;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysLoginLogDO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 登录日志Assembler.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/21 17:56
 */
@Mapper(componentModel = "spring")
public interface LoginLogDtoAssembler {
    
    LoginLogPageDTO toDto(SysLoginLogDO log);
    
    List<LoginLogPageDTO> toDto(List<SysLoginLogDO> logs);
    
    Page<LoginLogPageDTO> toDto(Page<SysLoginLogDO> page);
    
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
    
    /**
     * 转换.
     *
     * @param loginLog
     * @return
     */
    default LoginLogPageDTO fromLoginLog(LoginLog loginLog) {
        if (loginLog == null) {
            return null;
        }
        LoginLogPageDTO loginLogDTO = BeanUtils.copyBeanNoException(loginLog, LoginLogPageDTO.class);
        loginLogDTO.setId(loginLog.getLogId() == null ? "" : loginLog.getLogId().getId())
                .setAccount(loginLog.getAccount() == null ? "" : loginLog.getAccount().getAccount())
                .setUserName(loginLog.getUserName() == null ? "" : loginLog.getUserName().getName());
        return loginLogDTO;
    }
}
