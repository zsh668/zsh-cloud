package com.zsh.cloud.system.infrastructure.persistence.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.cloud.system.domain.model.usergroup.UserGroupRepository;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysUserGroupDO;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysUserGroupMapper;
import org.springframework.stereotype.Repository;

/**
 * 用户组-Repository实现类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/20 13:44
 */
@Repository
public class UserGroupRepositoryImpl extends ServiceImpl<SysUserGroupMapper, SysUserGroupDO>
        implements UserGroupRepository, IService<SysUserGroupDO> {
    
}
