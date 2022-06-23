package com.zsh.cloud.system.application.command;

import com.zsh.cloud.common.core.dto.Command;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 创建菜单Command.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/17 16:32
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "创建菜单", description = "创建菜单")
public class MenuCreateCommand extends Command {
    
    /**
     * 菜单名称.
     */
    private String menuName;
    
    /**
     * 父级菜单id.
     */
    private String parentId;
    
    /**
     * 是否公开菜单 1是，0否.
     */
    private Integer isPublic;
    
    /**
     * 对应路由path.
     */
    private String path;
    
    /**
     * 对应路由组件component.
     */
    private String component;
    
    /**
     * 排序.
     */
    private Integer sortValue;
    
    /**
     * 菜单图标.
     */
    private String icon;
    
    /**
     * 功能描述.
     */
    private String describe;
}
