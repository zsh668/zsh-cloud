package com.zsh.cloud.system.domain.specification;

import com.zsh.cloud.common.core.domain.specification.AbstractSpecification;
import com.zsh.cloud.common.core.exception.code.enums.ServiceErrorCode;
import com.zsh.cloud.common.core.util.ServiceAssert;
import com.zsh.cloud.system.domain.model.station.Station;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysUserDO;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysUserMapper;

/**
 * 岗位删除Specification.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/10 16:51
 */
public class StationDeleteSpecification extends AbstractSpecification<Station> {
    
    private SysUserMapper sysUserMapper;
    
    public StationDeleteSpecification(SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }
    
    @Override
    public boolean isSatisfiedBy(Station station) {
        Long count = sysUserMapper.selectCount(SysUserDO::getStationId, station.getStationId().getId());
        ServiceAssert.notTrue(count > 0, ServiceErrorCode.STATION_VERIFICATION_ERROR.getCode(), "岗位关联用户无法删除或禁用");
        return true;
    }
}
