package com.zsh.cloud.system.domain.model.usergroup;

import com.zsh.cloud.common.core.domain.ValueObject;
import org.apache.commons.lang3.StringUtils;

/**
 * 用户组ID.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/25 11:53
 */
public class UserGroupId implements ValueObject<UserGroupId> {
    
    /**
     * 用户组ID.
     */
    private final String id;
    
    public UserGroupId(final String id) {
        if (StringUtils.isEmpty(id)) {
            throw new IllegalArgumentException("用户组id不能为空");
        }
        this.id = id;
    }
    
    public String getId() {
        return id;
    }
    
    @Override
    public boolean sameValueAs(UserGroupId other) {
        return other != null && this.id.equals(other.id);
    }
    
    @Override
    public String toString() {
        return id;
    }
}
