package com.zsh.cloud.system.application.model.command;

import com.zsh.cloud.common.core.dto.Command;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * ID集合Command.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/9 19:07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ID集合", description = "ID集合")
public class IdsCommand extends Command {
    
    /**
     * ID集合
     */
    @ApiModelProperty(value = "ID集合")
    @NotNull(message = "ID不能为空")
    @Size(min = 1, message = "最少选择一条数据")
    private List<String> ids;
}
