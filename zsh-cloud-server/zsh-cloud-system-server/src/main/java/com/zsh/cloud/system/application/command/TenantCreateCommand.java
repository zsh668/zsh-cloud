package com.zsh.cloud.system.application.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 创建租户Command.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/26 11:18
 */
@Data
@ApiModel(value = "创建租户", description = "创建租户")
public class TenantCreateCommand {
    
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
