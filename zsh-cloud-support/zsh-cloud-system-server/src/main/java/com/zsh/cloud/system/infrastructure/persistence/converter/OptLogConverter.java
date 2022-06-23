package com.zsh.cloud.system.infrastructure.persistence.converter;

import com.zsh.cloud.common.core.exception.code.enums.ServiceErrorCode;
import com.zsh.cloud.common.core.util.Assert;
import com.zsh.cloud.common.core.util.BeanUtils;
import com.zsh.cloud.system.domain.model.log.opt.OptLog;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysOptLogDO;

/**
 * 操作日志Converter.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/21 18:11
 */
public class OptLogConverter {
    
    /**
     * 转换.
     *
     * @param optLog
     * @return
     */
    public static SysOptLogDO fromOptLog(OptLog optLog) {
        Assert.notNull(optLog, ServiceErrorCode.LOG_NOT_EXISTS);
        SysOptLogDO sysOptLogDO = BeanUtils.copyBeanNoException(optLog, SysOptLogDO.class);
        sysOptLogDO.setId(optLog.getLogId() == null ? null : optLog.getLogId().getId());
        sysOptLogDO.setUserName(optLog.getUserName() == null ? null : optLog.getUserName().getName());
        sysOptLogDO.setTenantId(optLog.getTenantId() == null ? null : optLog.getTenantId().getId());
        return sysOptLogDO;
    }
}
