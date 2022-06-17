package com.zsh.cloud.system.infrastructure.persistence.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.cloud.system.domain.model.station.Station;
import com.zsh.cloud.system.domain.model.station.StationId;
import com.zsh.cloud.system.domain.model.station.StationName;
import com.zsh.cloud.system.domain.model.station.StationRepository;
import com.zsh.cloud.system.infrastructure.persistence.converter.StationConverter;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysStationDO;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysStationMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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
    
    @Override
    public Station find(StationId stationId) {
        SysStationDO sysStationDO = baseMapper.selectById(stationId.getId());
        if (sysStationDO == null) {
            return null;
        }
        return StationConverter.toStation(sysStationDO);
    }
    
    @Override
    public Station find(StationName stationName) {
        SysStationDO sysStationDO = baseMapper.selectOne(SysStationDO::getStationName, stationName.getName());
        if (sysStationDO == null) {
            return null;
        }
        return StationConverter.toStation(sysStationDO);
    }
    
    @Override
    public StationId store(Station station) {
        SysStationDO sysStationDO = StationConverter.fromStation(station);
        this.saveOrUpdate(sysStationDO);
        return new StationId(sysStationDO.getId());
    }
    
    @Override
    public void remove(List<StationId> stationIds) {
        List<String> ids = new ArrayList<>();
        stationIds.forEach(stationId -> ids.add(stationId.getId()));
        this.removeByIds(ids);
    }
}
