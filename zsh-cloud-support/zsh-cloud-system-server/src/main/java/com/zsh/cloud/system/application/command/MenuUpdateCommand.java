package com.zsh.cloud.system.application.command;

import com.zsh.cloud.common.core.dto.Command;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 * 更新菜单Command.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/17 16:33
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "更新菜单", description = "更新菜单")
public class MenuUpdateCommand extends Command {
    
    /**
     * 菜单ID.
     */
    @ApiModelProperty(value = "菜单ID")
    @NotEmpty(message = "菜单ID不能为空")
    private String id;
    
    /**
     * 菜单名称.
     */
    @ApiModelProperty(value = "菜单名称")
    @NotEmpty(message = "菜单名称不能为空")
    @Length(max = 20, message = "菜单名称长度不能超过20")
    private String menuName;
    
    /**
     * 父级菜单id.
     */
    @ApiModelProperty(value = "父级菜单id")
    @NotEmpty(message = "父级菜单id不能为空")
    private String parentId;
    
    /**
     * 是否公开菜单 1是，0否.
     */
    @ApiModelProperty(value = "是否公开菜单")
    @NotEmpty(message = "是否公开菜单不能为空")
    private Integer isPublic;
    
    /**
     * 对应路由path.
     */
    @ApiModelProperty(value = "对应路由path")
    @NotEmpty(message = "对应路由不能为空")
    @Length(max = 200, message = "对应路由path长度不能超过200")
    private String path;
    
    /**
     * 对应路由组件component.
     */
    @ApiModelProperty(value = "对应路由组件component")
    @NotEmpty(message = "对应路由组件不能为空")
    @Length(max = 200, message = "对应路由组件component长度不能超过200")
    private String component;
    
    /**
     * 排序.
     */
    @ApiModelProperty(value = "排序")
    @NotEmpty(message = "排序不能为空")
    private Integer sortValue;
    
    /**
     * 菜单图标.
     */
    @ApiModelProperty(value = "菜单图标")
    @Length(max = 200, message = "菜单图标长度不能超过200")
    private String icon;
    
    /**
     * 功能描述.
     */
    @ApiModelProperty(value = "功能描述")
    @Length(max = 200, message = "功能描述长度不能超过200")
    private String describe;
}
