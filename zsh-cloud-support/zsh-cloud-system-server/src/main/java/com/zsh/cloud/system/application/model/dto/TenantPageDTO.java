package com.zsh.cloud.system.application.model.dto;

import com.zsh.cloud.common.core.dto.DTO;
import com.zsh.cloud.common.core.enums.StatusEnum;
import com.zsh.cloud.common.web.translate.Translate;
import com.zsh.cloud.system.application.translate.ServiceImplTranslator;
import com.zsh.cloud.system.infrastructure.persistence.repository.UserRepositoryImpl;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 租户分页DTO.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/26 11:12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "TenantPageDTO", description = "租户分页DTO")
public class TenantPageDTO extends DTO {
    
    /**
     * id.
     */
    @ApiModelProperty(value = "租户ID")
    private String id;
    
    /**
     * 租户编码.
     */
    @ApiModelProperty(value = "租户编码")
    private String tenantCode;
    
    /**
     * 租户名称
     */
    @ApiModelProperty(value = "租户名称")
    private String tenantName;
    
    /**
     * 租户创建者
     */
    @ApiModelProperty(value = "租户ID")
    private String createdBy;
    
    /**
     * 租户创建者.
     */
    @ApiModelProperty(value = "租户创建者")
    @Translate(translator = ServiceImplTranslator.class, dataSource = UserRepositoryImpl.class, from = "createdBy", param = "userName")
    private String creatorName;
    
    /**
     * 租户状态
     */
    @ApiModelProperty(value = "状态")
    private Boolean status;
    
    /**
     * 功能描述.
     */
    @ApiModelProperty(value = "功能描述")
    private String describe;
    
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;
}
