package com.zsh.cloud.system.application.command;

import com.zsh.cloud.common.core.dto.Command;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

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
public class TenantCreateCommand extends Command {
    
    /**
     * 租户编码.
     */
    @ApiModelProperty(value = "租户编码")
    @NotBlank(message = "租户编码不能为空")
    @Length(max = 30, message = "租户编码长度不能超过30")
    private String tenantCode;
    
    /**
     * 租户名称.
     */
    @ApiModelProperty(value = "租户名称")
    @NotBlank(message = "租户名称不能为空")
    @Length(max = 50, message = "租户名称长度不能超过50")
    private String tenantName;
    
    /**
     * 功能描述。
     */
    @ApiModelProperty(value = "功能描述")
    @Length(max = 100, message = "功能描述长度不能超过100")
    private String describe;
}
