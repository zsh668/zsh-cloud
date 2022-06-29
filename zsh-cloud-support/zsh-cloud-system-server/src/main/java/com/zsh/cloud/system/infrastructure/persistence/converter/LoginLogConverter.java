package com.zsh.cloud.system.infrastructure.persistence.converter;

import com.zsh.cloud.common.core.exception.code.enums.ServiceErrorCode;
import com.zsh.cloud.common.core.util.Assert;
import com.zsh.cloud.common.core.util.BeanUtils;
import com.zsh.cloud.system.domain.model.log.login.LoginLog;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysLoginLogDO;

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
}
