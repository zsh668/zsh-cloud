package com.zsh.cloud.system.domain.model.org;

import com.zsh.cloud.common.core.domain.Entity;
import com.zsh.cloud.common.core.enums.StatusEnum;
import lombok.Getter;

/**
 * 组织.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/15 15:32
 */
@Getter
public class Org implements Entity<Org> {
    
    /**
     * 组织ID.
     */
    private OrgId orgId;
    
    /**
     * 组织名称.
     */
    private OrgName orgName;
    
    /**
     * 组织父ID.
     */
    private OrgId parentId;
    
    /**
     * 排序.
     */
    private Integer sortValue;
    
    /**
     * 状态.
     */
    private StatusEnum status;
    
    /**
     * 功能描述。
     */
    private String describe;
    
    public Org(OrgName orgName, OrgId parentId, Integer sortValue, String describe) {
        this.orgName = orgName;
        this.parentId = parentId;
        this.sortValue = sortValue;
        this.status = StatusEnum.ENABLE;
        this.describe = describe;
    }
    
    public Org(OrgId orgId, OrgName orgName, OrgId parentId, Integer sortValue, StatusEnum status, String describe) {
        this.orgId = orgId;
        this.orgName = orgName;
        this.parentId = parentId;
        this.sortValue = sortValue;
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
     * 启用、禁用.
     */
    public void disable() {
        this.status = isEnable() ? StatusEnum.DISABLE : StatusEnum.ENABLE;
    }
    
    @Override
    public boolean sameIdentityAs(Org other) {
        return other != null && orgId.sameValueAs(other.orgId);
    }
}
