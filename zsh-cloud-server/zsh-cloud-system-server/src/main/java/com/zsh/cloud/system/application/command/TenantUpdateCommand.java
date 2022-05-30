package com.zsh.cloud.system.application.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 更新租户Command.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/26 11:18
 */
@Data
@ApiModel(value = "更新租户", description = "更新租户")
public class TenantUpdateCommand {
    
    /**
     * 租户ID.
     */
    @ApiModelProperty(value = "租户ID")
    @NotBlank(message = "租户ID不能为空")
    private String id;
    
    /**
     * 租户编码.
     */
    @ApiModelProperty(value = "租户编码")
    @NotBlank(message = "租户编码不能为空")
    private String tenantCode;
    
    /**
     * 租户名称.
     */
    @ApiModelProperty(value = "租户名称")
    @NotBlank(message = "租户名称不能为空")
    private String tenantName;
    
    /**
     * 功能描述。
     */
    private String describe;
}
