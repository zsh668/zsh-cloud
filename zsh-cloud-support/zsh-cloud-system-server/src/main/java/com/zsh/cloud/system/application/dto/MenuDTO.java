package com.zsh.cloud.system.application.dto;

import com.zsh.cloud.common.core.dto.DTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 菜单.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/17 16:30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "MenuDTO", description = "菜单信息")
public class MenuDTO extends DTO {
    
    /**
     * 菜单ID.
     */
    @ApiModelProperty(value = "菜单ID")
    private String id;
    
    /**
     * 菜单名称.
     */
    @ApiModelProperty(value = "菜单名称")
    private String menuName;
    
    /**
     * 父级菜单id.
     */
    @ApiModelProperty(value = "父级菜单id")
    private String parentId;
    
    /**
     * 是否公开菜单 1是，0否.
     */
    @ApiModelProperty(value = "是否公开菜单")
    private Integer isPublic;
    
    /**
     * 对应路由path.
     */
    @ApiModelProperty(value = "路由")
    private String path;
    
    /**
     * 对应路由组件component.
     */
    @ApiModelProperty(value = "路由组件")
    private String component;
    
    /**
     * 排序.
     */
    @ApiModelProperty(value = "排序")
    private Integer sortValue;
    
    /**
     * 菜单图标.
     */
    @ApiModelProperty(value = "菜单图标")
    private String icon;
    
    /**
     * 状态 1启用，0禁用.
     */
    @ApiModelProperty(value = "状态")
    private Boolean status;
    
    /**
     * 功能描述.
     */
    @ApiModelProperty(value = "功能描述")
    private String describe;
}
