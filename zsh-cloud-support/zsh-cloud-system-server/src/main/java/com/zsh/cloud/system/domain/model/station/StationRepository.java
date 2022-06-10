package com.zsh.cloud.system.domain.model.station;

import java.util.List;

/**
 * 岗位-Repository接口.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/20 13:45
 */
public interface StationRepository {
    
    /**
     * 通过id获取岗位.
     *
     * @param stationId
     * @return
     */
    Station find(StationId stationId);
    
    /**
     * 通过名称获取岗位.
     *
     * @param stationName
     * @return
     */
    Station find(StationName stationName);
    
    /**
     * 保存.
     *
     * @param station
     */
    StationId store(Station station);
    
    /**
     * 删除.
     *
     * @param stationIds
     */
    void remove(List<StationId> stationIds);
}
