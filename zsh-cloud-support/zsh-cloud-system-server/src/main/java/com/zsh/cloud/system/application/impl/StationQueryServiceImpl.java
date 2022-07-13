package com.zsh.cloud.system.application.impl;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.system.application.StationQueryService;
import com.zsh.cloud.system.application.assembler.StationDtoAssembler;
import com.zsh.cloud.system.application.model.dto.StationDTO;
import com.zsh.cloud.system.application.model.dto.StationPageDTO;
import com.zsh.cloud.system.application.model.query.StationPageQuery;
import com.zsh.cloud.system.domain.model.station.Station;
import com.zsh.cloud.system.domain.model.station.StationId;
import com.zsh.cloud.system.domain.model.station.StationRepository;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysStationDO;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysStationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 岗位查询服务实现类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/10 15:48
 */
@Service
public class StationQueryServiceImpl implements StationQueryService {
    
    @Autowired
    private SysStationMapper sysStationMapper;
    
    @Autowired
    private StationDtoAssembler stationDtoAssembler;
    
    @Autowired
    private StationRepository stationRepository;
    
    @Override
    public Page<StationPageDTO> queryPage(StationPageQuery query) {
        Page<SysStationDO> page = sysStationMapper.selectPage(query);
        return stationDtoAssembler.toDto(page);
    }
    
    @Override
    public List<StationPageDTO> queryList(StationPageQuery query) {
        List<SysStationDO> list = sysStationMapper.selectList(query);
        return stationDtoAssembler.toDto(list);
    }
    
    @Override
    public StationDTO find(String stationId) {
        Station station = stationRepository.find(new StationId(stationId));
        return stationDtoAssembler.fromStation(station);
    }
}
