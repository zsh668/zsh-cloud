package com.zsh.cloud.system.infrastructure.persistence.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.cloud.system.domain.model.log.opt.OptLog;
import com.zsh.cloud.system.domain.model.log.opt.OptLogRepository;
import com.zsh.cloud.system.infrastructure.persistence.converter.OptLogConverter;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysOptLogDO;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysOptLogMapper;
import org.springframework.stereotype.Repository;

/**
 * 操作日志-Repository实现类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/21 18:07
 */
@Repository
public class OptLogRepositoryImpl extends ServiceImpl<SysOptLogMapper, SysOptLogDO>
        implements OptLogRepository, IService<SysOptLogDO> {
    
    @Override
    public void store(OptLog optLog) {
        SysOptLogDO sysOptLogDO = OptLogConverter.fromOptLog(optLog);
        if (sysOptLogDO.getId() == null) {
            this.save(sysOptLogDO);
        } else {
            this.updateById(sysOptLogDO);
        }
    }
}
