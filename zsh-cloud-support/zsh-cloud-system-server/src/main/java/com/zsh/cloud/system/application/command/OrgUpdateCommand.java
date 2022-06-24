package com.zsh.cloud.system.application.command;

import com.zsh.cloud.common.core.dto.Command;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

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
    @NotEmpty(message = "组织ID不能为空")
    private String id;
    
    /**
     * 名称.
     */
    @ApiModelProperty(value = "组织名称")
    @NotEmpty(message = "组织名称不能为空")
    @Length(max = 50, message = "组织名称长度不能超过50")
    private String orgName;
    
    /**
     * 父id.
     */
    @ApiModelProperty(value = "父组织ID")
    @NotEmpty(message = "父组织ID不能为空")
    private String parentId;
    
    /**
     * 排序.
     */
    @ApiModelProperty(value = "排序")
    @NotEmpty(message = "排序不能为空")
    private Integer sortValue;
    
    /**
     * 功能描述.
     */
    @ApiModelProperty(value = "功能描述")
    @Length(max = 200, message = "描述长度不能超过200")
    private String describe;
}
