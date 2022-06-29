package com.zsh.cloud.system.infrastructure.persistence.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.cloud.system.domain.model.log.login.LoginLog;
import com.zsh.cloud.system.domain.model.log.login.LoginLogRepository;
import com.zsh.cloud.system.infrastructure.persistence.converter.LoginLogConverter;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysLoginLogDO;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysUserDO;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysLoginLogMapper;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
        SysUserDO sysUserDO = sysUserMapper.selectOne(SysUserDO::getAccount, sysLoginLogDO.getAccount());
        if (sysUserDO != null) {
            sysLoginLogDO.setUserId(sysUserDO.getId());
            sysLoginLogDO.setUserName(sysUserDO.getUserName());
        }
        if (sysLoginLogDO.getId() == null) {
            this.save(sysLoginLogDO);
        } else {
            this.updateById(sysLoginLogDO);
        }
    }
}
