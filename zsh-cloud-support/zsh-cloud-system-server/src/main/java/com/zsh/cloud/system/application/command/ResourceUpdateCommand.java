package com.zsh.cloud.system.application.command;

import com.zsh.cloud.common.core.dto.Command;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 更新资源Command.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/17 16:33
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "更新资源", description = "更新资源")
public class ResourceUpdateCommand extends Command {

}
