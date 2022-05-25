package com.zsh.cloud.system.infrastructure.persistence.converter;

import com.zsh.cloud.common.core.domain.IDict;
import com.zsh.cloud.common.core.enums.StatusEnum;
import com.zsh.cloud.system.domain.model.tenant.Tenant;
import com.zsh.cloud.system.domain.model.tenant.TenantCode;
import com.zsh.cloud.system.domain.model.tenant.TenantId;
import com.zsh.cloud.system.domain.model.tenant.TenantName;
import com.zsh.cloud.system.domain.model.user.UserId;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysTenantDO;
import org.apache.commons.lang3.StringUtils;

/**
 * 租户Converter.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/25 17:58
 */
public class TenantConverter {
    
    /**
     * 转换.
     *
     * @param sysTenantDO
     * @return
     */
    public static Tenant toTenant(SysTenantDO sysTenantDO) {
        if (sysTenantDO == null) {
            return null;
        }
        TenantId tenantId = StringUtils.isBlank(sysTenantDO.getId()) ? null : new TenantId(sysTenantDO.getId());
        UserId creatorId =
                StringUtils.isBlank(sysTenantDO.getCreatorId()) ? null : new UserId(sysTenantDO.getCreatorId());
        return Tenant.builder().tenantId(tenantId).tenantCode(new TenantCode(sysTenantDO.getTenantCode()))
                .tenantName(new TenantName(sysTenantDO.getTenantName()))
                .status(IDict.getByCode(StatusEnum.class, sysTenantDO.getStatus())).creatorId(creatorId).build();
    }
}
