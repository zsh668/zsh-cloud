package com.zsh.cloud.system.application.assembler;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.system.application.command.StationCreateCommand;
import com.zsh.cloud.system.application.command.StationUpdateCommand;
import com.zsh.cloud.system.application.dto.StationDTO;
import com.zsh.cloud.system.application.dto.StationPageDTO;
import com.zsh.cloud.system.domain.model.org.Org;
import com.zsh.cloud.system.domain.model.org.OrgId;
import com.zsh.cloud.system.domain.model.station.Station;
import com.zsh.cloud.system.domain.model.station.StationId;
import com.zsh.cloud.system.domain.model.station.StationName;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysStationDO;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 岗位Assembler.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/10 16:21
 */
@Mapper(componentModel = "spring")
public interface StationDtoAssembler {
    
    /**
     * 转换.
     *
     * @param user
     * @return
     */
    StationPageDTO toDto(SysStationDO user);
    
    /**
     * 转换.
     *
     * @param user
     * @return
     */
    List<StationPageDTO> toDto(List<SysStationDO> user);
    
    /**
     * 转换.
     *
     * @param page
     * @return
     */
    Page<StationPageDTO> toDto(Page<SysStationDO> page);
    
    /**
     * 转换.
     *
     * @param station
     * @return
     */
    default StationDTO fromStation(Station station) {
        if (station == null) {
            return null;
        }
        StationDTO stationDto = new StationDTO();
        stationDto.setId(station.getStationId() == null ? "" : station.getStationId().getId())
                .setStationName(station.getStationName() == null ? "" : station.getStationName().getName())
                .setOrgId(station.getOrgId() == null ? "" : station.getOrgId().getId())
                .setStatus(station.getStatus() == null ? null : station.getStatus().getCode())
                .setOrderNum(station.getOrderNum()).setDescribe(station.getDescribe());
        return stationDto;
    }
    
    /**
     * 转换.
     *
     * @param stationCommand
     * @return
     */
    default Station toStation(StationCreateCommand stationCommand) {
        String orgId = stationCommand.getOrgId();
        if (StringUtils.isBlank(orgId)) {
            orgId = Org.PARENT_ID;
        }
        return new Station(new StationName(stationCommand.getStationName()), new OrgId(orgId),
                stationCommand.getOrderNum(), stationCommand.getDescribe());
    }
    
    /**
     * 转换.
     *
     * @param stationCommand
     * @return
     */
    default Station toStation(StationUpdateCommand stationCommand) {
        String orgId = stationCommand.getOrgId();
        if (StringUtils.isBlank(orgId)) {
            orgId = Org.PARENT_ID;
        }
        return new Station(new StationId(stationCommand.getId()), new StationName(stationCommand.getStationName()),
                new OrgId(orgId), stationCommand.getOrderNum(), null, stationCommand.getDescribe());
    }
}
