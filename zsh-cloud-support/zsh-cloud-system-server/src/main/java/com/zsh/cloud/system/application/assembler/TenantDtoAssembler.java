package com.zsh.cloud.system.application.assembler;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.system.application.model.command.TenantCreateCommand;
import com.zsh.cloud.system.application.model.command.TenantUpdateCommand;
import com.zsh.cloud.system.application.model.dto.TenantDTO;
import com.zsh.cloud.system.application.model.dto.TenantPageDTO;
import com.zsh.cloud.system.domain.model.tenant.Tenant;
import com.zsh.cloud.system.domain.model.tenant.TenantCode;
import com.zsh.cloud.system.domain.model.tenant.TenantId;
import com.zsh.cloud.system.domain.model.tenant.TenantName;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysTenantDO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 租户Assembler.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/30 15:06
 */
@Mapper(componentModel = "spring")
public interface TenantDtoAssembler {
    
    /**
     * tenant转换.
     *
     * @param tenant
     * @return
     */
    TenantPageDTO toDto(SysTenantDO tenant);
    
    /**
     * tenant转换.(TenantPageDTO toDto())
     *
     * @param tenant
     * @return
     */
    List<TenantPageDTO> toDto(List<SysTenantDO> tenant);
    
    /**
     * tenant转换.(List toDto())
     *
     * @param tenant
     * @return
     */
    Page<TenantPageDTO> toDto(Page<SysTenantDO> tenant);
    
    /**
     * 转换.
     *
     * @param tenant
     * @return
     */
    default TenantDTO fromTenant(Tenant tenant) {
        if (tenant == null) {
            return null;
        }
        TenantDTO tenantDto = new TenantDTO();
        tenantDto.setId(tenant.getTenantId() == null ? "" : tenant.getTenantId().getId())
                .setTenantCode(tenant.getTenantCode() == null ? "" : tenant.getTenantCode().getCode())
                .setTenantName(tenant.getTenantName() == null ? "" : tenant.getTenantName().getName())
                .setStatus(tenant.getStatus() == null ? null : tenant.getStatus().getCode());
        return tenantDto;
    }
    
    /**
     * 转换.
     *
     * @param tenantCommand
     * @return
     */
    default Tenant toTenant(TenantCreateCommand tenantCommand) {
        return new Tenant(new TenantCode(tenantCommand.getTenantCode()), new TenantName(tenantCommand.getTenantName()),
                tenantCommand.getDescribe());
    }
    
    /**
     * 转换.
     *
     * @param tenantCommand
     * @return
     */
    default Tenant toTenant(TenantUpdateCommand tenantCommand) {
        return new Tenant(new TenantId(tenantCommand.getId()), new TenantCode(tenantCommand.getTenantCode()),
                new TenantName(tenantCommand.getTenantName()), null, null, null, tenantCommand.getDescribe());
    }
}
