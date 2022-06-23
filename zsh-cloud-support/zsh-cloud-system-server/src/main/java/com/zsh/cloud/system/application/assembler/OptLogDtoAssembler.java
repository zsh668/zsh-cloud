package com.zsh.cloud.system.application.assembler;

import com.zsh.cloud.common.core.domain.IDict;
import com.zsh.cloud.common.log.enums.LogTypeEnum;
import com.zsh.cloud.system.api.dto.OptLogDTO;
import com.zsh.cloud.system.domain.model.log.opt.OptLog;
import com.zsh.cloud.system.domain.model.tenant.TenantId;
import com.zsh.cloud.system.domain.model.user.UserName;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;

/**
 * 操作日志Assembler.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/21 17:56
 */
@Mapper(componentModel = "spring")
public interface OptLogDtoAssembler {
    
    /**
     * 转换.
     *
     * @param logDTO
     * @return
     */
    default OptLog toLog(OptLogDTO logDTO) {
        UserName userName = null;
        if (StringUtils.isNotBlank(logDTO.getUserName())) {
            userName = new UserName(logDTO.getUserName());
        }
        TenantId tenantId = null;
        if (StringUtils.isNotBlank(logDTO.getTenantId())) {
            tenantId = new TenantId(logDTO.getTenantId());
        }
        return new OptLog(logDTO.getRequestIp(), IDict.getByCode(LogTypeEnum.class, logDTO.getType()), userName,
                logDTO.getDescription(), logDTO.getClassPath(), logDTO.getActionMethod(), logDTO.getRequestUri(),
                logDTO.getHttpMethod(), logDTO.getParams(), logDTO.getResult(), logDTO.getExDesc(),
                logDTO.getExDetail(), logDTO.getStartTime(), logDTO.getFinishTime(), logDTO.getConsumingTime(),
                logDTO.getUa(), tenantId);
    }
}
