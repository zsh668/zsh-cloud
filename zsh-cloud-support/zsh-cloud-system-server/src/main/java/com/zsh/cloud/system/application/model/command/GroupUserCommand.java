package com.zsh.cloud.system.application.model.command;

import com.zsh.cloud.common.core.dto.Command;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

/**
 * 用户组用户Command.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/9 19:01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "用户组用户", description = "用户组用户")
public class GroupUserCommand extends Command {
    
    /**
     * 用户组ID.
     */
    @ApiModelProperty(value = "用户组ID")
    @NotEmpty(message = "用户组ID不能为空")
    private String groupId;
    
    /**
     * 用户id集合
     */
    @ApiModelProperty(value = "用户id集合")
    private Set<String> userIds;
}
