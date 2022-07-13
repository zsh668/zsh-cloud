package com.zsh.cloud.system.application;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.system.application.model.dto.StationDTO;
import com.zsh.cloud.system.application.model.dto.StationPageDTO;
import com.zsh.cloud.system.application.model.query.StationPageQuery;

import java.util.List;

/**
 * 岗位查询服务接口.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/10 15:48
 */
public interface StationQueryService {
    
    /**
     * 分页查询.
     *
     * @param query
     * @return
     */
    Page<StationPageDTO> queryPage(StationPageQuery query);
    
    /**
     * 查询列表.
     *
     * @param query
     * @return
     */
    List<StationPageDTO> queryList(StationPageQuery query);
    
    /**
     * 通过id获取岗位.
     *
     * @param stationId
     * @return
     */
    StationDTO find(String stationId);
}
