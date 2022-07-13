package com.zsh.cloud.system.application.model.command;

import com.zsh.cloud.common.core.dto.Command;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

/**
 * 用户角色Command.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/9 19:01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "用户角色", description = "用户角色")
public class UserRoleCommand extends Command {
    
    /**
     * 用户ID.
     */
    @ApiModelProperty(value = "用户ID")
    @NotEmpty(message = "用户ID不能为空")
    private String userId;
    
    /**
     * 角色id集合
     */
    @ApiModelProperty(value = "角色id集合")
    private Set<String> roleIds;
}
