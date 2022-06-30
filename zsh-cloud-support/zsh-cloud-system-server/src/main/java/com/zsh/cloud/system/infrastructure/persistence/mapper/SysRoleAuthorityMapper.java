package com.zsh.cloud.system.infrastructure.persistence.mapper;

import com.zsh.cloud.common.mybatis.core.mapper.BaseMapperExt;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysRoleAuthorityDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * 角色资源Mapper.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/30 10:16
 */
@Mapper
public interface SysRoleAuthorityMapper extends BaseMapperExt<SysRoleAuthorityDO> {
    
    /**
     * 获取 菜单ID集合.
     *
     * @param userId
     * @return
     */
    Set<String> queryUserMenu(@Param("userId") String userId);
}
