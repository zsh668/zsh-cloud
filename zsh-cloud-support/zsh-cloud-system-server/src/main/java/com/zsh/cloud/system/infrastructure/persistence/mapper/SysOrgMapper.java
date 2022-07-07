package com.zsh.cloud.system.infrastructure.persistence.mapper;

import com.zsh.cloud.common.mybatis.core.mapper.BaseMapperExt;
import com.zsh.cloud.common.mybatis.util.Wraps;
import com.zsh.cloud.system.application.model.query.OrgPageQuery;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysOrgDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 组织Mapper.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/20 13:46
 */
@Mapper
public interface SysOrgMapper extends BaseMapperExt<SysOrgDO> {
    
    /**
     * 根据条件查询.
     *
     * @param query
     * @return
     */
    default List<SysOrgDO> selectList(OrgPageQuery query) {
        return selectList(Wraps.<SysOrgDO>lbQ().likeIfPresent(SysOrgDO::getOrgName, query.getOrgName()));
    }
}
