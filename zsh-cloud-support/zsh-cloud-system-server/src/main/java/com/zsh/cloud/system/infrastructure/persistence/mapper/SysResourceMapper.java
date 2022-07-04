package com.zsh.cloud.system.infrastructure.persistence.mapper;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.common.mybatis.core.mapper.BaseMapperExt;
import com.zsh.cloud.common.mybatis.util.Wraps;
import com.zsh.cloud.system.application.query.ResourcePageQuery;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysResourceDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 资源Mapper.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/29 11:06
 */
@Mapper
public interface SysResourceMapper extends BaseMapperExt<SysResourceDO> {
    
    /**
     * 根据userId查询资源.
     *
     * @param userId
     * @return
     */
    List<SysResourceDO> queryResourceByUserId(@Param("userId") String userId);
    
    /**
     * 根据查询条件分页查询数据.
     *
     * @param query
     * @return
     */
    default Page<SysResourceDO> selectPage(ResourcePageQuery query) {
        return selectPage(query, Wraps.<SysResourceDO>lbQ().eqIfPresent(SysResourceDO::getMenuId, query.getMenuId())
                .likeIfPresent(SysResourceDO::getResourceName, query.getResourceName())
                .likeIfPresent(SysResourceDO::getResourceCode, query.getResourceCode()));
    }
    
    /**
     * 根据查询条件查询数据.
     *
     * @param query
     * @return
     */
    default List<SysResourceDO> selectList(ResourcePageQuery query) {
        return selectList(Wraps.<SysResourceDO>lbQ().eqIfPresent(SysResourceDO::getMenuId, query.getMenuId())
                .likeIfPresent(SysResourceDO::getResourceName, query.getResourceName())
                .likeIfPresent(SysResourceDO::getResourceCode, query.getResourceCode()));
    }
}
