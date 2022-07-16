package com.zsh.cloud.system.application.impl;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.system.application.LoginLogQueryService;
import com.zsh.cloud.system.application.assembler.LoginLogDtoAssembler;
import com.zsh.cloud.system.application.model.dto.LoginLogPageDTO;
import com.zsh.cloud.system.application.model.query.LoginLogPageQuery;
import com.zsh.cloud.system.domain.model.log.LogId;
import com.zsh.cloud.system.domain.model.log.login.LoginLog;
import com.zsh.cloud.system.domain.model.log.login.LoginLogRepository;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysLoginLogDO;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysLoginLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 登录日志查询服务实现类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/7 10:31
 */
@Service
public class LoginLogQueryServiceImpl implements LoginLogQueryService {
    
    @Autowired
    private LoginLogRepository loginLogRepository;
    
    @Autowired
    private SysLoginLogMapper sysLoginLogMapper;
    
    @Autowired
    private LoginLogDtoAssembler loginLogDtoAssembler;
    
    @Override
    public Page<LoginLogPageDTO> queryPage(LoginLogPageQuery loginLogPageQuery) {
        Page<SysLoginLogDO> page = sysLoginLogMapper.selectPage(loginLogPageQuery);
        return loginLogDtoAssembler.toDto(page);
    }
    
    @Override
    public LoginLogPageDTO find(String id) {
        LoginLog loginLog = loginLogRepository.find(new LogId(id));
        return loginLogDtoAssembler.fromLoginLog(loginLog);
    }
}
