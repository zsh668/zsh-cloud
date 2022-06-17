package com.zsh.cloud.system.application.command;

import com.zsh.cloud.common.core.dto.Command;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 更新组织Command.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/14 14:25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "更新组织", description = "更新组织")
public class OrgUpdateCommand extends Command {
    
    /**
     * id.
     */
    @ApiModelProperty(value = "组织ID")
    private String id;
    
    /**
     * 名称.
     */
    @ApiModelProperty(value = "组织名称")
    private String orgName;
    
    /**
     * 排序.
     */
    @ApiModelProperty(value = "排序")
    private Integer sortValue;
    
    /**
     * 功能描述.
     */
    @ApiModelProperty(value = "功能描述")
    private String describe;
}
