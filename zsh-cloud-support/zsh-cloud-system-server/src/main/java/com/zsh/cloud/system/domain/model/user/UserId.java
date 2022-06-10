package com.zsh.cloud.system.domain.model.user;

import com.zsh.cloud.common.core.domain.ValueObject;
import com.zsh.cloud.common.core.exception.code.enums.GlobalErrorCode;
import com.zsh.cloud.common.core.util.ServiceAssert;
import org.apache.commons.lang3.StringUtils;

/**
 * 用户ID.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/25 11:53
 */
public class UserId implements ValueObject<UserId> {
    
    /**
     * 超级管理员角色.
     */
    public static final String SYS_ADMIN = "1";
    
    /**
     * 用户ID.
     */
    private final String id;
    
    public UserId(final String id) {
        ServiceAssert.isTrue(StringUtils.isNotBlank(id), GlobalErrorCode.BAD_REQUEST.getCode(), "用户id不能为空");
        this.id = id;
    }
    
    public String getId() {
        return id;
    }
    
    public boolean isSysAdmin() {
        return SYS_ADMIN.equals(id);
    }
    
    @Override
    public boolean sameValueAs(UserId other) {
        return other != null && this.id.equals(other.id);
    }
    
    @Override
    public String toString() {
        return id;
    }
}
