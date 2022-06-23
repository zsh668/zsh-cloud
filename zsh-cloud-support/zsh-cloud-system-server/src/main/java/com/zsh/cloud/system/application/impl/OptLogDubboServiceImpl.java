package com.zsh.cloud.system.application.impl;

import com.zsh.cloud.system.api.dto.OptLogDTO;
import com.zsh.cloud.system.api.dubbo.OptLogDubboService;
import com.zsh.cloud.system.application.assembler.OptLogDtoAssembler;
import com.zsh.cloud.system.domain.model.log.opt.OptLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 系统日志保存服务实现.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/27 15:14
 */
@Slf4j
@DubboService(timeout = 3000)
public class OptLogDubboServiceImpl implements OptLogDubboService {
    
    @Autowired
    private OptLogRepository optLogRepository;
    
    @Autowired
    private OptLogDtoAssembler optLogDtoAssembler;
    
    @Override
    public void save(OptLogDTO logDTO) {
        optLogRepository.store(optLogDtoAssembler.toLog(logDTO));
    }
}
