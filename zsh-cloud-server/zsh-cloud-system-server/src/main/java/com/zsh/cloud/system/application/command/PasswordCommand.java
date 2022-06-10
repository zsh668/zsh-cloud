package com.zsh.cloud.system.application.command;

import com.zsh.cloud.common.core.dto.Command;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 * 密码Command.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/9 19:07
 */
@Data
@ApiModel(value = "修改密码", description = "修改密码")
public class PasswordCommand extends Command {
    
    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private String userId;
    
    /**
     * 原密码
     */
    @ApiModelProperty(value = "原密码")
    @NotEmpty(message = "原密码不能为空")
    @Length(min = 6, max = 18, message = "原密码长度为 6-18 位")
    private String password;
    
    /**
     * 新密码
     */
    @ApiModelProperty(value = "新密码")
    @NotEmpty(message = "新密码不能为空")
    @Length(min = 6, max = 18, message = "密码长度为 6-18 位")
    private String newPassword;
    
    /**
     * 确认密码
     */
    @ApiModelProperty(value = "确认密码")
    @NotEmpty(message = "确认密码不能为空")
    @Length(min = 6, max = 18, message = "密码长度为 6-18 位")
    private String confirmPassword;
}
