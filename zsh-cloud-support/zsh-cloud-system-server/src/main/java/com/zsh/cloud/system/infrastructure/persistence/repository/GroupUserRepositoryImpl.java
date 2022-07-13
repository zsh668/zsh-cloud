package com.zsh.cloud.system.infrastructure.persistence.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.cloud.system.domain.model.groupuser.GroupUser;
import com.zsh.cloud.system.domain.model.groupuser.GroupUserRepository;
import com.zsh.cloud.system.domain.model.user.UserId;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysUserGroupUserDO;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysUserGroupUserMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户组用户-Repository实现类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/12 13:39
 */
@Repository
public class GroupUserRepositoryImpl extends ServiceImpl<SysUserGroupUserMapper, SysUserGroupUserDO>
        implements GroupUserRepository, IService<SysUserGroupUserDO> {
    
    @Override
    public void store(GroupUser groupUser) {
        //先删除用户组与用户关系
        List<String> groupIds = new ArrayList<>();
        String groupId = groupUser.getUserGroupId().getId();
        groupIds.add(groupId);
        baseMapper.deleteByUserGroupIds(groupIds);
        List<UserId> userIds = groupUser.getUserIds();
        if (!CollectionUtils.isEmpty(userIds)) {
            //保存用户组与用户关系
            for (UserId userId : userIds) {
                SysUserGroupUserDO sysUserGroupUserDO = new SysUserGroupUserDO();
                sysUserGroupUserDO.setGroupId(groupId);
                sysUserGroupUserDO.setUserId(userId.getId());
                baseMapper.insert(sysUserGroupUserDO);
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
