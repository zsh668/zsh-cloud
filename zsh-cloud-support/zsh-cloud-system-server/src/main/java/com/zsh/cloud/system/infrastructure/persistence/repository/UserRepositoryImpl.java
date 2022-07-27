package com.zsh.cloud.system.infrastructure.persistence.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.cloud.common.mybatis.util.Wraps;
import com.zsh.cloud.system.domain.model.role.Role;
import com.zsh.cloud.system.domain.model.role.RoleRepository;
import com.zsh.cloud.system.domain.model.user.Account;
import com.zsh.cloud.system.domain.model.user.Password;
import com.zsh.cloud.system.domain.model.user.User;
import com.zsh.cloud.system.domain.model.user.UserId;
import com.zsh.cloud.system.domain.model.user.UserRepository;
import com.zsh.cloud.system.domain.model.usergroup.UserGroup;
import com.zsh.cloud.system.domain.model.usergroup.UserGroupRepository;
import com.zsh.cloud.system.domain.model.userrole.UserRole;
import com.zsh.cloud.system.domain.model.userrole.UserRoleRepository;
import com.zsh.cloud.system.infrastructure.persistence.converter.UserConverter;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysUserDO;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysUserGroupUserMapper;
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
    private RoleRepository roleRepository;
    
    @Autowired
    private UserGroupRepository userGroupRepository;
    
    @Autowired
    private UserRoleRepository userRoleRepository;
    
    @Autowired
    private SysUserGroupUserMapper sysUserGroupUserMapper;
    
    @Override
    public List<User> find(Account account) {
        return getUserList(baseMapper.queryUserNoTenantByAccount(account.getAccount()));
    }
    
    @Override
    public User find(UserId userId) {
        SysUserDO sysUserDO = baseMapper.selectById(userId.getId());
        return UserConverter.toUser(sysUserDO, getUserRoles(userId.getId()), getUserGroups(userId.getId()));
    }
    
    @Override
    public UserId store(User user) {
        SysUserDO sysUserDO = UserConverter.fromUser(user);
        this.saveOrUpdate(sysUserDO);
        UserId userId = new UserId(sysUserDO.getId());
        if (user.getUpdatedUserRole()) {
            userRoleRepository.store(new UserRole(userId, user.getRoleIds()));
        }
        return userId;
    }
    
    @Override
    public List<User> findBySuperior(UserId superior) {
        return getUserList(baseMapper.queryUserBySuperior(superior.getId()));
    }
    
    @Override
    public void remove(List<UserId> userIds) {
        List<String> ids = new ArrayList<>();
        userIds.forEach(userId -> ids.add(userId.getId()));
        this.removeByIds(ids);
        // 用户与角色关联
        userRoleRepository.remove(userIds);
        // 用户组与用户关联
        sysUserGroupUserMapper.deleteByUserIds(ids);
    }
    
    @Override
    public void reset(List<String> ids) {
        // 默认密码
        String password = Password.create(Password.DEFAULT).getPassword();
        // 默认 1 年 有效
        this.update(Wraps.<SysUserDO>lbU().set(SysUserDO::getPassword, password)
                .set(SysUserDO::getPasswordExpireTime, User.createExpireTime()).in(SysUserDO::getId, ids));
    }
    
    /**
     * 根据用户ID获取角色信息.
     *
     * @param userId
     * @return
     */
    private List<Role> getUserRoles(String userId) {
        return roleRepository.find(new UserId(userId));
    }
    
    /**
     * 根据用户ID获取用户组信息.
     *
     * @param userId
     * @return
     */
    private List<UserGroup> getUserGroups(String userId) {
        return userGroupRepository.find(new UserId(userId));
    }
    
    /**
     * 转换.
     *
     * @param sysUserDOList
     * @return
     */
    private List<User> getUserList(List<SysUserDO> sysUserDOList) {
        List<User> users = new ArrayList<>();
        if (CollectionUtils.isEmpty(sysUserDOList)) {
            return users;
        }
        for (SysUserDO sysUserDO : sysUserDOList) {
            User user = UserConverter.toUser(sysUserDO, getUserRoles(sysUserDO.getId()),
                    getUserGroups(sysUserDO.getId()));
            users.add(user);
        }
        return users;
    }
}
