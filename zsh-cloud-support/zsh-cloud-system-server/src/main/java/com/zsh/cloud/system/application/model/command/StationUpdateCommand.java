package com.zsh.cloud.system.application.model.command;

import com.zsh.cloud.common.core.dto.Command;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 更新岗位Command.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/10 15:54
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "更新岗位", description = "更新岗位")
public class StationUpdateCommand extends Command {
    
    /**
     * id.
     */
    @ApiModelProperty(value = "岗位id")
    @NotEmpty(message = "岗位id不能为空")
    private String id;
    
    /**
     * 名称.
     */
    @ApiModelProperty(value = "岗位名称")
    @NotEmpty(message = "岗位名称不能为空")
    @Length(max = 50, message = "名称长度不能超过50")
    private String stationName;
    
    /**
     * 组织ID.
     */
    @ApiModelProperty(value = "组织ID")
    @NotEmpty(message = "组织ID不能为空")
    private String orgId;
    
    /**
     * 排序.
     */
    @ApiModelProperty(value = "排序")
    @NotNull(message = "排序不能为空")
    private Integer sortValue;
    
    /**
     * 功能描述.
     */
    @ApiModelProperty(value = "功能描述")
    @Length(max = 200, message = "描述长度不能超过200")
    private String describe;
}
