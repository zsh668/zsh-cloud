package com.zsh.cloud.system.infrastructure.persistence.mapper;

import com.zsh.cloud.common.mybatis.core.mapper.BaseMapperExt;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysRoleOrgDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色组织Mapper.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/20 13:46
 */
@Mapper
public interface SysRoleOrgMapper extends BaseMapperExt<SysRoleOrgDO> {
    
    /**
     * 根据角色ID，批量删除.
     *
     * @param roleIds
     * @return
     */
    int deleteByRoleIds(List<String> roleIds);
}
