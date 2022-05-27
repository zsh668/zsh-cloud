package com.zsh.cloud.system.infrastructure.persistence.mapper;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.common.mybatis.core.mapper.BaseMapperExt;
import com.zsh.cloud.system.application.query.TenantPageQuery;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysTenantDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 租户Mapper.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/20 13:46
 */
@Mapper
public interface SysTenantMapper extends BaseMapperExt<SysTenantDO> {
    
    Page<SysTenantDO> selectPage(TenantPageQuery query);
}
