package com.zsh.cloud.system.application.assembler;

import com.zsh.cloud.common.core.domain.IDict;
import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.common.core.util.BeanUtils;
import com.zsh.cloud.common.log.enums.LogTypeEnum;
import com.zsh.cloud.system.api.dto.OptLogDTO;
import com.zsh.cloud.system.application.model.dto.OptLogInfoDTO;
import com.zsh.cloud.system.application.model.dto.OptLogPageDTO;
import com.zsh.cloud.system.domain.model.log.opt.OptLog;
import com.zsh.cloud.system.domain.model.tenant.TenantId;
import com.zsh.cloud.system.domain.model.user.UserName;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysOptLogDO;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.springframework.http.HttpMethod;

import java.util.List;

/**
 * 操作日志Assembler.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/21 17:56
 */
@Mapper(componentModel = "spring")
public interface OptLogDtoAssembler {
    
    OptLogPageDTO toDto(SysOptLogDO log);
    
    List<OptLogPageDTO> toDto(List<SysOptLogDO> logs);
    
    Page<OptLogPageDTO> toDto(Page<SysOptLogDO> page);
    
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
                HttpMethod.resolve(logDTO.getHttpMethod()), logDTO.getParams(), logDTO.getResult(), logDTO.getExDesc(),
                logDTO.getExDetail(), logDTO.getStartTime(), logDTO.getFinishTime(), logDTO.getConsumingTime(),
                logDTO.getUa(), tenantId);
    }
    
    /**
     * 转换.
     *
     * @param optLog
     * @return
     */
    default OptLogInfoDTO fromOptLog(OptLog optLog) {
        if (optLog == null) {
            return null;
        }
        OptLogInfoDTO optInfo = BeanUtils.copyBeanNoException(optLog, OptLogInfoDTO.class);
        optInfo.setId(optLog.getLogId() == null ? "" : optLog.getLogId().getId())
                .setUserName(optLog.getUserName() == null ? "" : optLog.getUserName().getName())
                .setType(optLog.getType().getCode()).setHttpMethod(optLog.getHttpMethod().name());
        return optInfo;
    }
}
