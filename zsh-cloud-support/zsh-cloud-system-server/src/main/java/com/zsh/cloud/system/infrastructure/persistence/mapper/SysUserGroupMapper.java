package com.zsh.cloud.system.infrastructure.persistence.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.common.mybatis.core.mapper.BaseMapperExt;
import com.zsh.cloud.common.mybatis.util.Wraps;
import com.zsh.cloud.system.application.query.UserGroupPageQuery;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysUserDO;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysUserGroupDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
    
    /**
     * 根据用户ID 查询所属用户组.
     *
     * @param userId
     * @return
     */
    List<SysUserGroupDO> queryUserGroup(String userId);
    
    /**
     * 分页查询用户组.
     *
     * @param query
     * @return
     */
    default Page<SysUserGroupDO> selectPage(UserGroupPageQuery query) {
        return selectPage(query,
                Wraps.<SysUserGroupDO>lbQ().likeIfPresent(SysUserGroupDO::getGroupName, query.getGroupName())
                        .eqIfPresent(SysUserGroupDO::getRoleId, query.getRoleId())
                        .eqIfPresent(SysUserGroupDO::getStatus, query.getStatus()));
        
    }
}
