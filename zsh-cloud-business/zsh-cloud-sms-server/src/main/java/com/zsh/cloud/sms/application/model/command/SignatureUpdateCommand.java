package com.zsh.cloud.sms.application.model.command;

import com.zsh.cloud.common.core.dto.Command;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 更新签名Command.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/8/1 16:43
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "更新签名", description = "更新签名")
public class SignatureUpdateCommand extends Command {

}
