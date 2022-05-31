package com.zsh.cloud.system.domain.model.role;

import com.zsh.cloud.common.core.domain.Entity;
import com.zsh.cloud.common.core.enums.BooleanEnum;
import com.zsh.cloud.common.core.enums.StatusEnum;
import com.zsh.cloud.common.mybatis.datascope.enums.DataScopeTypeEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 角色.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/31 12:52
 */
@Data
@Builder
public class Role implements Entity<Role> {
    
    private RoleId roleId;
    
    /**
     * 角色编码.
     */
    private RoleCode roleCode;
    
    /**
     * 角色名称.
     */
    private RoleName roleName;
    
    /**
     * 互斥角色.
     */
    private RoleId repel;
    
    /**
     * 数据权限范围.
     */
    private DataScopeTypeEnum dsType;
    
    /**
     * 是否内置.
     */
    private BooleanEnum readonly;
    
    /**
     * 状态.
     */
    private StatusEnum status;
    
    /**
     * 功能描述。
     */
    private String describe;
    
    /**
     * 创建时间
     */
    private LocalDateTime createdTime;
    
    /**
     * 是否有效.
     *
     * @return
     */
    public boolean isEnable() {
        return status == StatusEnum.ENABLE;
    }
    
    /**
     * 启用、禁用.
     */
    public void disable() {
        this.status = this.status == StatusEnum.DISABLE ? StatusEnum.ENABLE : StatusEnum.DISABLE;
    }
    
    @Override
    public boolean sameIdentityAs(Role other) {
        return other != null && roleId.sameValueAs(other.roleId);
    }
}
