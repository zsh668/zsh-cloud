package com.zsh.cloud.system.infrastructure.persistence.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.cloud.system.domain.model.user.Account;
import com.zsh.cloud.system.domain.model.user.User;
import com.zsh.cloud.system.domain.model.user.UserId;
import com.zsh.cloud.system.domain.model.user.UserRepository;
import com.zsh.cloud.system.infrastructure.persistence.converter.UserConverter;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysRoleDO;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysUserDO;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysUserGroupDO;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysRoleMapper;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysUserGroupMapper;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户-Repository实现类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/24 18:31
 */
@Repository
public class UserRepositoryImpl extends ServiceImpl<SysUserMapper, SysUserDO>
        implements UserRepository, IService<SysUserDO> {
    
    @Autowired
    private SysRoleMapper sysRoleMapper;
    
    @Autowired
    private SysUserGroupMapper sysUserGroupMapper;
    
    @Override
    public List<User> find(Account account) {
        List<SysUserDO> sysUserDOList = baseMapper.queryUserNoTenantByAccount(account.getAccount());
        if (CollectionUtils.isEmpty(sysUserDOList)) {
            return null;
        }
        List<User> users = new ArrayList<>();
        for (SysUserDO sysUserDO : sysUserDOList) {
            User user = UserConverter.toUser(sysUserDO, getUserRoles(sysUserDO.getId()),
                    getUserGroups(sysUserDO.getId()));
            users.add(user);
        }
        return users;
    }
    
    @Override
    public User find(UserId userId) {
        SysUserDO sysUserDO = baseMapper.selectById(userId.getId());
        if (sysUserDO == null) {
            return null;
        }
        return UserConverter.toUser(sysUserDO, getUserRoles(sysUserDO.getId()), getUserGroups(sysUserDO.getId()));
    }
    
    /**
     * 根据用户ID获取角色信息.
     *
     * @param userId
     * @return
     */
    private List<SysRoleDO> getUserRoles(String userId) {
        return sysRoleMapper.queryUserRole(userId);
    }
    
    /**
     * 根据用户ID获取用户组信息.
     *
     * @param userId
     * @return
     */
    private List<SysUserGroupDO> getUserGroups(String userId) {
        return sysUserGroupMapper.queryUserGroup(userId);
    }
}
