package com.zsh.cloud.system.infrastructure.persistence.converter;

import com.zsh.cloud.common.core.exception.code.enums.ServiceErrorCode;
import com.zsh.cloud.common.core.util.Assert;
import com.zsh.cloud.common.core.util.BeanUtils;
import com.zsh.cloud.system.domain.model.log.LogId;
import com.zsh.cloud.system.domain.model.log.login.LoginLog;
import com.zsh.cloud.system.domain.model.user.Account;
import com.zsh.cloud.system.domain.model.user.UserId;
import com.zsh.cloud.system.domain.model.user.UserName;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysLoginLogDO;
import org.apache.commons.lang3.StringUtils;

/**
 * 登录日志Converter.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/29 11:35
 */
public class LoginLogConverter {
    
    /**
     * 转换.
     *
     * @param loginLog
     * @return
     */
    public static SysLoginLogDO fromLoginLog(LoginLog loginLog) {
        Assert.notNull(loginLog, ServiceErrorCode.LOG_NOT_EXISTS);
        SysLoginLogDO sysLoginLogDO = BeanUtils.copyBeanNoException(loginLog, SysLoginLogDO.class);
        sysLoginLogDO.setId(loginLog.getLogId() == null ? null : loginLog.getLogId().getId());
        sysLoginLogDO.setUserId(loginLog.getUserId() == null ? null : loginLog.getUserId().getId());
        sysLoginLogDO.setUserName(loginLog.getUserName() == null ? null : loginLog.getUserName().getName());
        sysLoginLogDO.setAccount(loginLog.getAccount() == null ? null : loginLog.getAccount().getAccount());
        return sysLoginLogDO;
    }
    
    /**
     * 转换.
     *
     * @param sysLoginLogDO
     * @return
     */
    public static LoginLog toLoginLog(SysLoginLogDO sysLoginLogDO) {
        if (sysLoginLogDO == null) {
            return null;
        }
        UserId userId = StringUtils.isBlank(sysLoginLogDO.getUserId()) ? null : new UserId(sysLoginLogDO.getUserId());
        UserName userName =
                StringUtils.isBlank(sysLoginLogDO.getUserName()) ? null : new UserName(sysLoginLogDO.getUserName());
        return new LoginLog(new LogId(sysLoginLogDO.getId()), sysLoginLogDO.getRequestIp(), userId, userName,
                new Account(sysLoginLogDO.getAccount()), sysLoginLogDO.getDescription(), sysLoginLogDO.getLoginTime(),
                sysLoginLogDO.getUa(), sysLoginLogDO.getBrowser(), sysLoginLogDO.getBrowserVersion(),
                sysLoginLogDO.getOperatingSystem(), sysLoginLogDO.getLocation());
    }
}
