package com.zsh.cloud.system.infrastructure.persistence.mapper;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.common.mybatis.core.mapper.BaseMapperExt;
import com.zsh.cloud.common.mybatis.util.Wraps;
import com.zsh.cloud.system.application.model.query.StationPageQuery;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysStationDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 岗位Mapper.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/20 13:46
 */
@Mapper
public interface SysStationMapper extends BaseMapperExt<SysStationDO> {
    
    /**
     * 顶级组织ID.
     */
    String ORG_ID = "1";
    
    /**
     * 根据查询条件分页查询数据.
     *
     * @param query 分页参数
     * @return
     */
    default Page<SysStationDO> selectPage(StationPageQuery query) {
        return selectPage(query,
                Wraps.<SysStationDO>lbQ().likeIfPresent(SysStationDO::getStationCode, query.getStationCode())
                        .likeIfPresent(SysStationDO::getStationName, query.getStationName())
                        .eqIfPresent(SysStationDO::getOrgId, query.getOrgId())
                        .eqIfPresent(SysStationDO::getStatus, query.getStatus()));
    }
    
    /**
     * 根据查询条件查询数据(包含顶级组织ID).
     *
     * @param query 参数
     * @return
     */
    default List<SysStationDO> selectList(StationPageQuery query) {
        return selectList(Wraps.<SysStationDO>lbQ().likeIfPresent(SysStationDO::getStationCode, query.getStationCode())
                .likeIfPresent(SysStationDO::getStationName, query.getStationName())
                .inIfPresent(SysStationDO::getOrgId, query.getOrgId(), ORG_ID)
                .eqIfPresent(SysStationDO::getStatus, query.getStatus()));
    }
}
