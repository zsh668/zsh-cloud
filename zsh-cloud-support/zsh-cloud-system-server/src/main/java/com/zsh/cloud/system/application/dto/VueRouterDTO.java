package com.zsh.cloud.system.application.dto;

import com.zsh.cloud.common.core.domain.TreeNode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Vue路由树.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/29 18:17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "VueRouterDTO", description = "Vue路由树信息")
public class VueRouterDTO extends TreeNode<VueRouterDTO, String> {
    
    /**
     * id.
     */
    @ApiModelProperty(value = "菜单ID")
    private String id;
    
    /**
     * 菜单名称.
     */
    @ApiModelProperty(value = "菜单名称")
    private String menuName;
    
    /**
     * 名称.
     */
    @ApiModelProperty(value = "父菜单ID")
    private String parentId;
    
    /**
     * 路径.
     */
    @ApiModelProperty(value = "路径")
    private String path;
    
    /**
     * 组件.
     */
    @ApiModelProperty(value = "组件")
    private String component;
    
    /**
     * 重定向.
     */
    @ApiModelProperty(value = "重定向")
    private String redirect;
    
    /**
     * 元数据.
     */
    @ApiModelProperty(value = "元数据")
    private RouterMetaDTO meta;
    
    /**
     * 是否隐藏.
     */
    @ApiModelProperty(value = "是否隐藏")
    private Boolean hidden = false;
    
    /**
     * 总是显示
     */
    @ApiModelProperty(value = "总是显示")
    private Boolean alwaysShow = false;
    
    /**
     * 子路由.
     */
    @ApiModelProperty(value = "子路由")
    private List<VueRouterDTO> children;
    
    public Boolean getAlwaysShow() {
        return getChildren() != null && !getChildren().isEmpty();
    }
    
    public String getComponent() {
        if (getChildren() != null && !getChildren().isEmpty()) {
            return "Layout";
        }
        return this.component;
    }
}
