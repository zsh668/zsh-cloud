package com.zsh.cloud.system.application.impl;

import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.zsh.cloud.common.core.util.AddressUtil;
import com.zsh.cloud.system.application.LoginLogApplicationService;
import com.zsh.cloud.system.application.assembler.LoginLogDtoAssembler;
import com.zsh.cloud.system.application.model.dto.LoginLogDTO;
import com.zsh.cloud.system.domain.model.log.LogId;
import com.zsh.cloud.system.domain.model.log.login.LoginLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 登录日志应用服务实现类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/7 10:30
 */
@Service
public class LoginLogApplicationServiceImpl implements LoginLogApplicationService {
    
    @Autowired
    private LoginLogRepository loginLogRepository;
    
    @Autowired
    private LoginLogDtoAssembler loginLogDtoAssembler;
    
    @Override
    public void save(LoginLogDTO loginLog) {
        UserAgent userAgent = UserAgentUtil.parse(loginLog.getUa());
        String location = AddressUtil.getRegion(loginLog.getRequestIp());
        loginLog.setLoginTime(LocalDateTime.now()).setBrowser(userAgent.getBrowser().getName())
                .setBrowserVersion(userAgent.getVersion()).setOperatingSystem(userAgent.getPlatform().getName())
                .setLocation(location);
        loginLogRepository.store(loginLogDtoAssembler.toLog(loginLog));
    }
    
    @Override
    public void deleteBatch(List<String> ids) {
        List<LogId> logIds = new ArrayList<>();
        ids.forEach(id -> logIds.add(new LogId(id)));
        loginLogRepository.remove(logIds);
    }
}
