package com.zsh.cloud.system.infrastructure.persistence.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.cloud.system.domain.model.user.UserId;
import com.zsh.cloud.system.domain.model.usergroup.UserGroup;
import com.zsh.cloud.system.domain.model.usergroup.UserGroupId;
import com.zsh.cloud.system.domain.model.usergroup.UserGroupName;
import com.zsh.cloud.system.domain.model.usergroup.UserGroupRepository;
import com.zsh.cloud.system.infrastructure.persistence.converter.UserGroupConverter;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysUserGroupDO;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysUserGroupUserDO;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysUserGroupMapper;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysUserGroupUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

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
    
    @Autowired
    private SysUserGroupUserMapper sysUserGroupUserMapper;
    
    @Override
    public UserGroup find(UserGroupId userGroupId) {
        SysUserGroupDO userGroupDO = baseMapper.selectById(userGroupId.getId());
        return UserGroupConverter.toUserGroup(userGroupDO);
    }
    
    @Override
    public UserGroup find(UserGroupName userGroupName) {
        SysUserGroupDO userGroupDO = baseMapper.selectOne(SysUserGroupDO::getGroupName, userGroupName.getName());
        return UserGroupConverter.toUserGroup(userGroupDO);
    }
    
    @Override
    public UserGroupId store(UserGroup userGroup) {
        SysUserGroupDO sysUserGroupDO = UserGroupConverter.fromUserGroup(userGroup);
        this.saveOrUpdate(sysUserGroupDO);
        String userGroupId = sysUserGroupDO.getId();
        //先删除用户组与用户关系
        List<String> userGroupIds = new ArrayList<>();
        userGroupIds.add(userGroupId);
        sysUserGroupUserMapper.deleteByUserGroupIds(userGroupIds);
        List<UserId> userIds = userGroup.getUserIds();
        if (!CollectionUtils.isEmpty(userIds)) {
            //保存用户组与用户关系
            for (UserId userId : userIds) {
                SysUserGroupUserDO sysUserGroupUserDO = new SysUserGroupUserDO();
                sysUserGroupUserDO.setGroupId(userGroupId);
                sysUserGroupUserDO.setUserId(userId.getId());
                sysUserGroupUserMapper.insert(sysUserGroupUserDO);
            }
        }
        return new UserGroupId(userGroupId);
    }
    
    @Override
    public void remove(List<UserGroupId> userGroupIds) {
        List<String> ids = new ArrayList<>();
        userGroupIds.forEach(userGroupId -> ids.add(userGroupId.getId()));
        this.removeByIds(ids);
        // 用户组与用户关联
        sysUserGroupUserMapper.deleteByUserGroupIds(ids);
    }
}
