package com.zsh.cloud.system.application.model.dto;

import com.zsh.cloud.common.core.domain.TreeNode;
import com.zsh.cloud.common.web.translate.TranslateField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 菜单资源树信息DTO.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/17 16:28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "MenuResourceTreeDTO", description = "菜单资源树信息")
public class MenuResourceTreeDTO extends TreeNode<MenuResourceTreeDTO, String> {
    
    /**
     * id.
     */
    @ApiModelProperty(value = "菜单ID")
    private String id;
    
    /**
     * 名称.
     */
    @ApiModelProperty(value = "菜单名称")
    private String menuName;
    
    /**
     * 父id.
     */
    @ApiModelProperty(value = "父菜单ID")
    private String parentId;
    
    /**
     * 排序.
     */
    @ApiModelProperty(value = "排序")
    private Integer sortValue;
    
    /**
     * 状态 1启用，0禁用.
     */
    @ApiModelProperty(value = "状态")
    private Boolean status;
    
    /**
     * 子集合.
     */
    @TranslateField
    @ApiModelProperty(value = "子菜单集合")
    private List<MenuResourceTreeDTO> children;
    
    /**
     * 资源集合.
     */
    @ApiModelProperty(value = "资源集合")
    private List<ResourceDTO> resources;
}
