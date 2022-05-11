package com.zsh.cloud.system.infrastructure.persistence.repository;

import com.zsh.cloud.system.domain.model.user.Account;
import com.zsh.cloud.system.domain.model.user.User;
import com.zsh.cloud.system.domain.model.user.UserId;
import com.zsh.cloud.system.domain.model.user.UserRepository;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysUserDO;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
public class UserRepositoryImpl implements UserRepository {
    
    @Autowired
    private SysUserMapper sysUserMapper;
    
    @Override
    public List<User> find(Account account) {
        List<SysUserDO> sysUserDOList = sysUserMapper.queryUserNoTenantByAccount(account.getAccount());
        if (sysUserDOList == null || sysUserDOList.isEmpty()) {
            return null;
        }
        List<User> users = new ArrayList<>();
        //        for (SysUserDO sysUserDO : sysUserDOList) {
        //            User user = UserConverter.toUser(sysUserDO, getUserAccount(sysUserDO.getAccountId()), getUserRoleIds(sysUserDO.getId()));
        //            users.add(user);
        //        }
        return users;
    }
    
    @Override
    public User find(UserId userId) {
        return null;
    }
}
