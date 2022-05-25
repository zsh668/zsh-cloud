package com.zsh.cloud.system.infrastructure.persistence.mapper;

import com.zsh.cloud.common.mybatis.core.mapper.BaseMapperExt;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysUserGroupUserDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户组与用户关联Mapper.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/20 13:46
 */
@Mapper
public interface SysUserGroupUserMapper extends BaseMapperExt<SysUserGroupUserDO> {
    
    /**
     * 根据用户ID，批量删除
     */
    int deleteByUserIds(List<String> userIds);
}
