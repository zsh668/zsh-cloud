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
     * 编码.
     */
    private StationCode stationCode;
    
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
    private Integer sortValue;
    
    /**
     * 状态.
     */
    private StatusEnum status;
    
    /**
     * 功能描述.
     */
    private String describe;
    
    public Station(StationCode stationCode, StationName stationName, OrgId orgId, Integer sortValue, String describe) {
        this.stationCode = stationCode;
        this.stationName = stationName;
        this.orgId = orgId;
        this.sortValue = sortValue;
        this.status = StatusEnum.ENABLE;
        this.describe = describe;
    }
    
    public Station(StationId stationId, StationCode stationCode, StationName stationName, OrgId orgId, Integer sortValue, StatusEnum status,
            String describe) {
        this.stationId = stationId;
        this.stationCode = stationCode;
        this.stationName = stationName;
        this.orgId = orgId;
        this.sortValue = sortValue;
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
