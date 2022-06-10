package com.zsh.cloud.system.application.command;

import com.zsh.cloud.common.core.dto.Command;
import com.zsh.cloud.common.core.enums.StatusEnum;
import com.zsh.cloud.common.web.translate.Translate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 创建岗位Command.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/10 15:53
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "创建岗位", description = "创建岗位")
public class StationCreateCommand extends Command {
    
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
