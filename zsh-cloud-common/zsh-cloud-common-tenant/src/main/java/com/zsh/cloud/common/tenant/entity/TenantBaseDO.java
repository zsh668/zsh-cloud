package com.zsh.cloud.common.tenant.entity;

import com.zsh.cloud.common.mybatis.core.entity.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 拓展多租户的 BaseDO 基类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/3/24 14:18
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TenantBaseDO extends BaseDO {
    
    /**
     * 多租户编号
     */
    private String tenantId;
}
