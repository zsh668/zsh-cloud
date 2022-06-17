package com.zsh.cloud.system.application.command;

import com.zsh.cloud.common.core.dto.Command;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 创建资源Command.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/17 16:32
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "创建资源", description = "创建资源")
public class ResourceCreateCommand extends Command {

}
