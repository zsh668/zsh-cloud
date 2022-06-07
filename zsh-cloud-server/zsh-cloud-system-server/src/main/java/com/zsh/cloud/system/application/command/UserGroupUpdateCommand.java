package com.zsh.cloud.system.application.command;

import com.zsh.cloud.common.core.dto.Command;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 更新用户组Command.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/7 18:36
 */
@Data
@ApiModel(value = "更新用户组", description = "更新用户组")
public class UserGroupUpdateCommand extends Command {

}
