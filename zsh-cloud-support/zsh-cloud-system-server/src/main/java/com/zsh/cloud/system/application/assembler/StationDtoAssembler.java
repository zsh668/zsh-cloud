package com.zsh.cloud.system.application.assembler;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.system.application.model.command.StationCreateCommand;
import com.zsh.cloud.system.application.model.command.StationUpdateCommand;
import com.zsh.cloud.system.application.model.dto.StationDTO;
import com.zsh.cloud.system.application.model.dto.StationPageDTO;
import com.zsh.cloud.system.domain.model.org.Org;
import com.zsh.cloud.system.domain.model.org.OrgId;
import com.zsh.cloud.system.domain.model.station.Station;
import com.zsh.cloud.system.domain.model.station.StationCode;
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
                .setStationCode(station.getStationCode() == null ? "" : station.getStationCode().getCode())
                .setStationName(station.getStationName() == null ? "" : station.getStationName().getName())
                .setOrgId(station.getOrgId() == null ? "" : station.getOrgId().getId())
                .setStatus(station.getStatus() == null ? null : station.getStatus().getCode())
                .setSortValue(station.getSortValue()).setDescribe(station.getDescribe());
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
        return new Station(new StationCode(stationCommand.getStationCode()),
                new StationName(stationCommand.getStationName()), new OrgId(orgId), stationCommand.getSortValue(),
                stationCommand.getDescribe());
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
        return new Station(new StationId(stationCommand.getId()), new StationCode(stationCommand.getStationCode()),
                new StationName(stationCommand.getStationName()), new OrgId(orgId), stationCommand.getSortValue(), null,
                stationCommand.getDescribe());
    }
}
