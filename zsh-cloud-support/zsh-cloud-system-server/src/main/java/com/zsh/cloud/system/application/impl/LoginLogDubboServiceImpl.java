package com.zsh.cloud.system.application.impl;

import com.zsh.cloud.system.api.dto.LoginLogDTO;
import com.zsh.cloud.system.api.dubbo.LoginLogDubboService;
import com.zsh.cloud.system.application.assembler.LoginLogDtoAssembler;
import com.zsh.cloud.system.domain.model.log.login.LoginLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 登录日志保存服务实现.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/27 15:14
 */
@Slf4j
@DubboService(timeout = 3000)
public class LoginLogDubboServiceImpl implements LoginLogDubboService {
    
    @Autowired
    private LoginLogRepository loginLogRepository;
    
    @Autowired
    private LoginLogDtoAssembler loginLogDtoAssembler;
    
    @Override
    public void save(LoginLogDTO logDTO) {
        loginLogRepository.store(loginLogDtoAssembler.toLog(logDTO));
    }
}
