package com.zsh.cloud.system.infrastructure.persistence.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.cloud.system.domain.model.station.StationRepository;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysStationDO;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysStationMapper;
import org.springframework.stereotype.Repository;

/**
 * 岗位-Repository实现类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/20 13:44
 */
@Repository
public class StationRepositoryImpl extends ServiceImpl<SysStationMapper, SysStationDO>
        implements StationRepository, IService<SysStationDO> {
    
}
