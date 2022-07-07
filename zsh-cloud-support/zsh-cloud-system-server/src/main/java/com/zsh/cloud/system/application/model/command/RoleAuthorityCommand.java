package com.zsh.cloud.system.application.model.command;

import com.zsh.cloud.common.core.dto.Command;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * 角色资源Command.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/1 16:52
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "RoleAuthorityCommand", description = "角色资源")
public class RoleAuthorityCommand extends Command {
    
    /**
     * 角色ID.
     */
    @NotNull(message = "角色id不能为空")
    @ApiModelProperty(value = "角色ID")
    private String roleId;
    
    /**
     * 菜单ID集合.
     */
    @ApiModelProperty(value = "菜单ID")
    private Set<String> menuIds;
    
    /**
     * 资源ID集合.
     */
    @ApiModelProperty(value = "资源ID")
    private Set<String> resourceIds;
}
