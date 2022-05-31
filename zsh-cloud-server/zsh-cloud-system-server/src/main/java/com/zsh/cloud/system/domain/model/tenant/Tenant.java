package com.zsh.cloud.system.domain.model.tenant;

import com.zsh.cloud.common.core.domain.Entity;
import com.zsh.cloud.common.core.enums.StatusEnum;
import com.zsh.cloud.system.domain.model.user.UserId;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 租户.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/25 17:30
 */
@Data
@Builder
public class Tenant implements Entity<Tenant> {
    
    /**
     * TenantId
     */
    private TenantId tenantId;
    
    /**
     * 租户编码
     */
    private TenantCode tenantCode;
    
    /**
     * 租户名称
     */
    private TenantName tenantName;
    
    /**
     * 状态
     */
    private StatusEnum status;
    
    /**
     * 创建者Id
     */
    private UserId creatorId;
    
    /**
     * 创建时间
     */
    private LocalDateTime createdTime;
    
    /**
     * 功能描述。
     */
    private String describe;
    
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
    public boolean sameIdentityAs(Tenant other) {
        return other != null && tenantId.sameValueAs(other.tenantId);
    }
}
