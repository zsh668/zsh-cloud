package com.zsh.cloud.system.application.model.command;

import com.zsh.cloud.common.core.dto.Command;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 * 更新用户组Command.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/7 18:36
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "更新用户组", description = "更新用户组")
public class UserGroupUpdateCommand extends Command {
    
    /**
     * 用户ID.
     */
    @ApiModelProperty(value = "用户组ID")
    @NotEmpty(message = "用户组ID不能为空")
    private String id;
    
    /**
     * 用户组名称
     */
    @ApiModelProperty(value = "用户组名称")
    @NotEmpty(message = "用户组名称不能为空")
    @Length(max = 30, message = "用户组名称长度不能超过30")
    private String groupName;
    
    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID")
    @NotEmpty(message = "角色ID不能为空")
    private String roleId;
    
    /**
     * 功能描述
     */
    @ApiModelProperty(value = "功能描述")
    @Length(max = 100, message = "功能描述长度不能超过100")
    private String describe;
}
