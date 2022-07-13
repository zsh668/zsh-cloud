package com.zsh.cloud.system.domain.specification;

import com.zsh.cloud.common.core.domain.specification.AbstractSpecification;
import com.zsh.cloud.common.core.exception.code.enums.ServiceErrorCode;
import com.zsh.cloud.common.core.util.Assert;
import com.zsh.cloud.system.domain.model.station.Station;
import com.zsh.cloud.system.domain.model.station.StationRepository;

/**
 * 岗位创建Specification.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/10 16:51
 */
public class StationCreateSpecification extends AbstractSpecification<Station> {
    
    private StationRepository stationRepository;
    
    public StationCreateSpecification(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }
    
    @Override
    public boolean isSatisfiedBy(Station station) {
        if (station.getStationCode() != null) {
            Station existStation = stationRepository.find(station.getStationCode());
            Assert.notTrue(existStation != null && !existStation.getStationId().sameValueAs(station.getStationId()),
                    ServiceErrorCode.STATION_CODE_EXISTS);
        }
        if (station.getStationName() != null) {
            Station existStation = stationRepository.find(station.getStationName());
            Assert.notTrue(existStation != null && !existStation.getStationId().sameValueAs(station.getStationId()),
                    ServiceErrorCode.STATION_NAME_EXISTS);
        }
        return true;
    }
}
