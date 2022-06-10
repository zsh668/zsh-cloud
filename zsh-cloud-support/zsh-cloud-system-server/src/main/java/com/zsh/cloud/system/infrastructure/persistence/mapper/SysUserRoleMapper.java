package com.zsh.cloud.system.infrastructure.persistence.mapper;

import com.zsh.cloud.common.mybatis.core.mapper.BaseMapperExt;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysUserRoleDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户角色关联Mapper.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/20 13:46
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapperExt<SysUserRoleDO> {
    
    /**
     * 根据用户ID，批量删除
     */
    int deleteByUserIds(List<String> userIds);
}
