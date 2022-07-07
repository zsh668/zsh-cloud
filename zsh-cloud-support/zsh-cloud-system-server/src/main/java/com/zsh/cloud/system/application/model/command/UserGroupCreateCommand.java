package com.zsh.cloud.system.application.model.command;

import com.zsh.cloud.common.core.dto.Command;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

/**
 * 创建用户组Command.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/7 18:36
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "创建用户组", description = "创建用户组")
public class UserGroupCreateCommand extends Command {
    
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
     * 成员.
     */
    @ApiModelProperty(value = "成员")
    @NotEmpty(message = "成员不能为空")
    private Set<String> userIdList;
    
    /**
     * 功能描述
     */
    @ApiModelProperty(value = "功能描述")
    @Length(max = 100, message = "功能描述长度不能超过100")
    private String describe;
}
