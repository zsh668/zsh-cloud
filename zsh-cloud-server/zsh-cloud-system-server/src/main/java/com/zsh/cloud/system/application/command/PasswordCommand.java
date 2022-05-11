package com.zsh.cloud.system.application.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 密码Command.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/9 19:07
 */
@Data
@ApiModel(value = "修改密码", description = "修改密码")
public class PasswordCommand {
    
    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private String userId;
    
    /**
     * 原密码
     */
    @ApiModelProperty(value = "原密码")
    private String password;
    
    /**
     * 新密码
     */
    @ApiModelProperty(value = "新密码")
    private String newPassword;
}
