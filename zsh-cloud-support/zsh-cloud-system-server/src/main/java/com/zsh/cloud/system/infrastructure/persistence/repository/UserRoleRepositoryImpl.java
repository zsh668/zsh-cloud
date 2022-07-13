package com.zsh.cloud.system.infrastructure.persistence.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.cloud.system.domain.model.role.RoleId;
import com.zsh.cloud.system.domain.model.user.UserId;
import com.zsh.cloud.system.domain.model.userrole.UserRole;
import com.zsh.cloud.system.domain.model.userrole.UserRoleRepository;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysUserRoleDO;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysUserRoleMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户角色-Repository实现类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/12 13:39
 */
@Repository
public class UserRoleRepositoryImpl extends ServiceImpl<SysUserRoleMapper, SysUserRoleDO>
        implements UserRoleRepository, IService<SysUserRoleDO> {
    
    @Override
    public void store(UserRole userRole) {
        //先删除用户与角色关系
        List<String> userIds = new ArrayList<>();
        String userId = userRole.getUserId().getId();
        userIds.add(userId);
        baseMapper.deleteByUserIds(userIds);
        List<RoleId> roleIds = userRole.getRoleIds();
        if (!CollectionUtils.isEmpty(roleIds)) {
            //保存用户与角色关系
            for (RoleId roleId : roleIds) {
                SysUserRoleDO sysUserRoleDO = new SysUserRoleDO();
                sysUserRoleDO.setUserId(userId);
                sysUserRoleDO.setRoleId(roleId.getId());
                baseMapper.insert(sysUserRoleDO);
            }
        }
    }
    
    @Override
    public void remove(List<UserId> userIds) {
        List<String> ids = new ArrayList<>();
        userIds.forEach(userId -> ids.add(userId.getId()));
        baseMapper.deleteByUserIds(ids);
    }
}
