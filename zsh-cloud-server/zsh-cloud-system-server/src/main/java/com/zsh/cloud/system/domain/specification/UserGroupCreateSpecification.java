package com.zsh.cloud.system.domain.specification;

import com.zsh.cloud.common.core.domain.specification.AbstractSpecification;
import com.zsh.cloud.common.core.exception.code.enums.ServiceErrorCode;
import com.zsh.cloud.common.core.util.Assert;
import com.zsh.cloud.system.domain.model.usergroup.UserGroup;
import com.zsh.cloud.system.domain.model.usergroup.UserGroupRepository;

/**
 * 用户组创建Specification.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/10 14:55
 */
public class UserGroupCreateSpecification extends AbstractSpecification<UserGroup> {
    
    private UserGroupRepository userGroupRepository;
    
    public UserGroupCreateSpecification(UserGroupRepository userGroupRepository) {
        this.userGroupRepository = userGroupRepository;
    }
    
    @Override
    public boolean isSatisfiedBy(UserGroup userGroup) {
        UserGroup existGroup = userGroupRepository.find(userGroup.getUserGroupName());
        Assert.isTrue(existGroup != null && !existGroup.getUserGroupId().sameValueAs(userGroup.getUserGroupId()),
                ServiceErrorCode.USER_GROUP_NAME_EXISTS);
        return true;
    }
}
