package com.zsh.cloud.system.domain.model.role;

import com.zsh.cloud.common.core.domain.Entity;
import com.zsh.cloud.common.core.enums.BooleanEnum;
import com.zsh.cloud.common.core.enums.StatusEnum;
import com.zsh.cloud.common.mybatis.datascope.enums.DataScopeTypeEnum;
import com.zsh.cloud.system.domain.model.org.OrgId;
import lombok.Getter;

import java.util.List;

/**
 * 角色.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/31 12:52
 */
@Getter
public class Role implements Entity<Role> {
    
    /**
     * 角色id.
     */
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
     * 功能描述.
     */
    private String describe;
    
    public Role(RoleCode roleCode, RoleName roleName, RoleId repel, DataScopeTypeEnum dsType, List<OrgId> orgIdList,
            String describe) {
        this.roleCode = roleCode;
        this.roleName = roleName;
        this.repel = repel;
        this.dsType = dsType;
        this.orgIdList = orgIdList;
        this.readonly = BooleanEnum.FALSE;
        this.status = StatusEnum.ENABLE;
        this.describe = describe;
    }
    
    public Role(RoleId roleId, RoleCode roleCode, RoleName roleName, RoleId repel, DataScopeTypeEnum dsType,
            List<OrgId> orgIdList, BooleanEnum readonly, StatusEnum status, String describe) {
        this.roleId = roleId;
        this.roleCode = roleCode;
        this.roleName = roleName;
        this.repel = repel;
        this.dsType = dsType;
        this.orgIdList = orgIdList;
        this.readonly = readonly;
        this.status = status;
        this.describe = describe;
    }
    
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
