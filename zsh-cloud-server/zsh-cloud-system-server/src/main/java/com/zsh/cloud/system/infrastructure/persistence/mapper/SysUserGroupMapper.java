package com.zsh.cloud.system.infrastructure.persistence.mapper;

import com.zsh.cloud.common.mybatis.core.mapper.BaseMapperExt;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysUserGroupDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户组Mapper.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/20 10:53
 */
@Mapper
public interface SysUserGroupMapper extends BaseMapperExt<SysUserGroupDO> {
    
    List<SysUserGroupDO> queryUserGroup(String userId);
}
