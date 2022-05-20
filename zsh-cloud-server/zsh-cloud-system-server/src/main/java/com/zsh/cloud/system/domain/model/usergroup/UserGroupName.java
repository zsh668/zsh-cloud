package com.zsh.cloud.system.domain.model.usergroup;

import com.zsh.cloud.common.core.domain.ValueObject;
import org.apache.commons.lang3.StringUtils;

/**
 * 用户组名称.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/25 11:53
 */
public class UserGroupName implements ValueObject<UserGroupName> {
    
    /**
     * 用户组名称.
     */
    private final String name;
    
    public UserGroupName(final String name) {
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("用户组名称不能为空");
        }
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    public boolean sameValueAs(UserGroupName other) {
        return other != null && this.name.equals(other.name);
    }
    
    @Override
    public String toString() {
        return name;
    }
}
