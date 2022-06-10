package com.zsh.cloud.system.infrastructure.persistence.mapper;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.common.mybatis.core.mapper.BaseMapperExt;
import com.zsh.cloud.common.mybatis.util.Wraps;
import com.zsh.cloud.system.application.query.RolePageQuery;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysRoleDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色Mapper.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/20 09:20
 */
@Mapper
public interface SysRoleMapper extends BaseMapperExt<SysRoleDO> {
    
    /**
     * 查询用户的所有角色
     *
     * @param userId
     * @return
     */
    List<SysRoleDO> queryUserRole(String userId);
    
    /**
     * 根据查询条件分页查询数据.
     *
     * @param query 分页参数
     * @return
     */
    default Page<SysRoleDO> selectPage(RolePageQuery query) {
        return selectPage(query, Wraps.<SysRoleDO>lbQ().likeIfPresent(SysRoleDO::getRoleCode, query.getRoleCode())
                .likeIfPresent(SysRoleDO::getRoleName, query.getRoleName())
                .eqIfPresent(SysRoleDO::getStatus, query.getStatus()));
    }
}
