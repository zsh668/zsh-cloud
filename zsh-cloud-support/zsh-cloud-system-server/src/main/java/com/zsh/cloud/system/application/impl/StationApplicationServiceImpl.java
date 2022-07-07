package com.zsh.cloud.system.application.impl;

import com.zsh.cloud.system.application.StationApplicationService;
import com.zsh.cloud.system.application.assembler.StationDtoAssembler;
import com.zsh.cloud.system.application.model.command.StationCreateCommand;
import com.zsh.cloud.system.application.model.command.StationUpdateCommand;
import com.zsh.cloud.system.domain.model.station.Station;
import com.zsh.cloud.system.domain.model.station.StationId;
import com.zsh.cloud.system.domain.model.station.StationRepository;
import com.zsh.cloud.system.domain.specification.StationCreateSpecification;
import com.zsh.cloud.system.domain.specification.StationDeleteSpecification;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 岗位应用服务实现类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/10 15:49
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class StationApplicationServiceImpl implements StationApplicationService {
    
    @Autowired
    private StationRepository stationRepository;
    
    @Autowired
    private StationDtoAssembler stationDtoAssembler;
    
    @Autowired
    private SysUserMapper sysUserMapper;
    
    @Override
    public void save(StationCreateCommand stationCommand) {
        Station station = stationDtoAssembler.toStation(stationCommand);
        StationCreateSpecification specification = new StationCreateSpecification(stationRepository);
        specification.isSatisfiedBy(station);
        stationRepository.store(station);
    }
    
    @Override
    public void update(StationUpdateCommand stationCommand) {
        Station station = stationDtoAssembler.toStation(stationCommand);
        StationCreateSpecification specification = new StationCreateSpecification(stationRepository);
        specification.isSatisfiedBy(station);
        stationRepository.store(station);
    }
    
    @Override
    public void deleteBatch(List<String> ids) {
        List<StationId> stationIds = new ArrayList<>();
        StationDeleteSpecification specification = new StationDeleteSpecification(sysUserMapper);
        ids.forEach(id -> {
            StationId stationId = new StationId(id);
            Station station = stationRepository.find(stationId);
            specification.isSatisfiedBy(station);
            stationIds.add(stationId);
        });
        stationRepository.remove(stationIds);
    }
    
    @Override
    public void disable(String id) {
        Station station = stationRepository.find(new StationId(id));
        StationDeleteSpecification specification = new StationDeleteSpecification(sysUserMapper);
        specification.isSatisfiedBy(station);
        station.disable();
        stationRepository.store(station);
    }
}
