package com.zsh.cloud.system.infrastructure.persistence.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.cloud.system.domain.model.log.LogId;
import com.zsh.cloud.system.domain.model.log.login.LoginLog;
import com.zsh.cloud.system.domain.model.log.login.LoginLogRepository;
import com.zsh.cloud.system.infrastructure.persistence.converter.LoginLogConverter;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysLoginLogDO;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysUserDO;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysLoginLogMapper;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 登录日志-Repository实现类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/29 11:34
 */
@Repository
public class LoginLogRepositoryImpl extends ServiceImpl<SysLoginLogMapper, SysLoginLogDO>
        implements LoginLogRepository, IService<SysLoginLogDO> {
    
    @Autowired
    private SysUserMapper sysUserMapper;
    
    @Override
    public void store(LoginLog loginLog) {
        SysLoginLogDO sysLoginLogDO = LoginLogConverter.fromLoginLog(loginLog);
        List<SysUserDO> users = sysUserMapper.selectList(sysLoginLogDO.getAccount());
        if (!CollectionUtils.isEmpty(users)) {
            SysUserDO sysUserDO = users.get(0);
            sysLoginLogDO.setUserId(sysUserDO.getId());
            sysLoginLogDO.setUserName(sysUserDO.getUserName());
            // 更新 最后登录时间
            sysUserDO.setLastLoginTime(LocalDateTime.now());
            sysUserMapper.updateById(sysUserDO);
        }
        if (sysLoginLogDO.getId() == null) {
            this.save(sysLoginLogDO);
        } else {
            this.updateById(sysLoginLogDO);
        }
    }
    
    @Override
    public LoginLog find(LogId logId) {
        SysLoginLogDO sysLoginLogDO = baseMapper.selectById(logId.getId());
        return LoginLogConverter.toLoginLog(sysLoginLogDO);
    }
    
    @Override
    public void remove(List<LogId> logIds) {
        List<String> ids = new ArrayList<>();
        logIds.forEach(logId -> ids.add(logId.getId()));
        this.removeByIds(ids);
    }
}
