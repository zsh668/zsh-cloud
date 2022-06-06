package com.zsh.cloud.system.domain.model.role;

import com.zsh.cloud.common.core.domain.Entity;
import com.zsh.cloud.common.core.enums.BooleanEnum;
import com.zsh.cloud.common.core.enums.StatusEnum;
import com.zsh.cloud.common.mybatis.datascope.enums.DataScopeTypeEnum;
import com.zsh.cloud.system.domain.model.org.OrgId;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

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
     * 权限自定义-》关联的组织id.
     */
    private List<OrgId> orgIdList;
    
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
     * 是否内置角色.
     *
     * @return
     */
    public boolean isReadonly() {
        return readonly == BooleanEnum.TRUE;
    }
    
    /**
     * 启用、禁用.
     */
    public void disable() {
        this.status = isEnable() ? StatusEnum.DISABLE : StatusEnum.ENABLE;
    }
    
    @Override
    public boolean sameIdentityAs(Role other) {
        return other != null && roleId.sameValueAs(other.roleId);
    }
}
