package com.zsh.cloud.system.application.command;

import com.zsh.cloud.common.core.dto.Command;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 重置密码Command.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/9 19:07
 */
@Data
@ApiModel(value = "重置密码", description = "重置密码")
public class ResetPasswordCommand extends Command {
    
    /**
     * 用户ID集合
     */
    @ApiModelProperty(value = "用户ID集合")
    @NotNull(message = "用户ID不能为空")
    @Size(min = 1, message = "最少选择一条用户数据")
    private List<String> userIds;
}
