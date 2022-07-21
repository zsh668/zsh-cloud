package com.zsh.cloud.system.application.model.command;

import com.zsh.cloud.common.core.dto.Command;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

/**
 * 用户租户Command.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/9 19:01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "用户租户", description = "用户租户")
public class UserTenantCommand extends Command {
    
    /**
     * 用户ID.
     */
    @ApiModelProperty(value = "用户ID")
    @NotEmpty(message = "用户ID不能为空")
    private String userId;
    
    /**
     * 租户ID
     */
    @ApiModelProperty(value = "租户ID")
    @NotEmpty(message = "租户ID不能为空")
    private String tenantId;
}
