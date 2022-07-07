package com.zsh.cloud.system.application.impl;

import com.zsh.cloud.system.application.LoginLogApplicationService;
import com.zsh.cloud.system.domain.model.log.LogId;
import com.zsh.cloud.system.domain.model.log.login.LoginLogRepository;
import com.zsh.cloud.system.domain.model.menu.MenuId;
import com.zsh.cloud.system.domain.model.menu.MenuRepository;
import com.zsh.cloud.system.domain.specification.MenuDeleteSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    
    @Override
    public void deleteBatch(List<String> ids) {
        List<LogId> logIds = new ArrayList<>();
        ids.forEach(id -> logIds.add(new LogId(id)));
        loginLogRepository.remove(logIds);
    }
}
