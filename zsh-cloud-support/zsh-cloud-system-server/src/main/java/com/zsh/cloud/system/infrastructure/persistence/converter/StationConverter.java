package com.zsh.cloud.system.infrastructure.persistence.converter;

import com.zsh.cloud.common.core.domain.IDict;
import com.zsh.cloud.common.core.enums.StatusEnum;
import com.zsh.cloud.common.core.exception.code.enums.ServiceErrorCode;
import com.zsh.cloud.common.core.util.Assert;
import com.zsh.cloud.system.domain.model.org.OrgId;
import com.zsh.cloud.system.domain.model.station.Station;
import com.zsh.cloud.system.domain.model.station.StationId;
import com.zsh.cloud.system.domain.model.station.StationName;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysStationDO;

/**
 * 岗位Converter.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/10 16:32
 */
public class StationConverter {
    
    /**
     * 转换.
     *
     * @param sysStationDO
     * @return
     */
    public static Station toStation(SysStationDO sysStationDO) {
        if (sysStationDO == null) {
            return null;
        }
        return new Station(new StationId(sysStationDO.getId()), new StationName(sysStationDO.getStationName()),
                new OrgId(sysStationDO.getOrgId()), sysStationDO.getOrderNum(),
                IDict.getByCode(StatusEnum.class, sysStationDO.getStatus()), sysStationDO.getDescribe());
    }
    
    /**
     * 转换.
     *
     * @param station
     * @return
     */
    public static SysStationDO fromStation(Station station) {
        Assert.notNull(station, ServiceErrorCode.STATION_NOT_EXISTS);
        SysStationDO sysStationDO = new SysStationDO();
        sysStationDO.setId(station.getStationId() == null ? null : station.getStationId().getId());
        sysStationDO.setStationName(station.getStationName() == null ? null : station.getStationName().getName());
        sysStationDO.setOrgId(station.getOrgId() == null ? null : station.getOrgId().getId());
        sysStationDO.setOrderNum(station.getOrderNum());
        sysStationDO.setStatus(station.getStatus() == null ? null : station.getStatus().getCode());
        sysStationDO.setDescribe(station.getDescribe());
        return sysStationDO;
    }
}
