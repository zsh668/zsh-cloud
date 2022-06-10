package com.zsh.cloud.system.application.command;

import com.zsh.cloud.common.core.dto.Command;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
    private String id;
    
    /**
     * 名称.
     */
    @ApiModelProperty(value = "岗位名称")
    private String stationName;
    
    /**
     * 组织ID.
     */
    @ApiModelProperty(value = "组织ID")
    private String orgId;
    
    /**
     * 排序.
     */
    @ApiModelProperty(value = "排序")
    private Integer orderNum;
    
    /**
     * 功能描述.
     */
    @ApiModelProperty(value = "功能描述")
    private String describe;
}
