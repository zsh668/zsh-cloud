package com.zsh.cloud.system.domain.model.station;

import com.zsh.cloud.common.core.domain.Entity;
import com.zsh.cloud.common.core.enums.StatusEnum;
import com.zsh.cloud.system.domain.model.org.OrgId;
import lombok.Getter;

/**
 * 岗位.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/10 16:24
 */
@Getter
public class Station implements Entity<Station> {
    
    /**
     * id
     */
    private StationId stationId;
    
    /**
     * 名称.
     */
    private StationName stationName;
    
    /**
     * 组织ID.
     */
    private OrgId orgId;
    
    /**
     * 排序.
     */
    private Integer orderNum;
    
    /**
     * 状态.
     */
    private StatusEnum status;
    
    /**
     * 功能描述.
     */
    private String describe;
    
    public Station(StationName stationName, OrgId orgId, Integer orderNum, String describe) {
        this.stationName = stationName;
        this.orgId = orgId;
        this.orderNum = orderNum;
        this.status = StatusEnum.ENABLE;
        this.describe = describe;
    }
    
    public Station(StationId stationId, StationName stationName, OrgId orgId, Integer orderNum, StatusEnum status,
            String describe) {
        this.stationId = stationId;
        this.stationName = stationName;
        this.orgId = orgId;
        this.orderNum = orderNum;
        this.status = status;
        this.describe = describe;
    }
    
    /**
     * 是否有效.
     *
     * @return
     */
    public boolean isEnable() {
        return status == StatusEnum.ENABLE;
    }
    
    /**
     * 启用、禁用.
     */
    public void disable() {
        this.status = isEnable() ? StatusEnum.DISABLE : StatusEnum.ENABLE;
    }
    
    @Override
    public boolean sameIdentityAs(Station other) {
        return other != null && stationId.sameValueAs(other.stationId);
    }
}
