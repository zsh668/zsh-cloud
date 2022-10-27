package com.zsh.cloud.system.application.impl;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.system.application.OptLogQueryService;
import com.zsh.cloud.system.application.assembler.OptLogDtoAssembler;
import com.zsh.cloud.system.application.model.dto.OptLogInfoDTO;
import com.zsh.cloud.system.application.model.dto.OptLogPageDTO;
import com.zsh.cloud.system.application.model.query.OptLogPageQuery;
import com.zsh.cloud.system.domain.model.log.LogId;
import com.zsh.cloud.system.domain.model.log.opt.OptLog;
import com.zsh.cloud.system.domain.model.log.opt.OptLogRepository;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysOptLogDO;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysOptLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 操作日志查询服务实现类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/7 10:32
 */
@Service
public class OptLogQueryServiceImpl implements OptLogQueryService {
    
    @Autowired
    private OptLogRepository optLogRepository;
    
    @Autowired
    private SysOptLogMapper sysOptLogMapper;
    
    @Autowired
    private OptLogDtoAssembler optLogDtoAssembler;
    
    @Override
    public Page<OptLogPageDTO> queryPage(OptLogPageQuery optLogPageQuery) {
        Page<SysOptLogDO> page = sysOptLogMapper.selectPage(optLogPageQuery);
        return optLogDtoAssembler.toDto(page);
    }
    
    @Override
    public OptLogInfoDTO find(String id) {
        OptLog optLog = optLogRepository.find(new LogId(id));
        return optLogDtoAssembler.fromOptLog(optLog);
    }
}
