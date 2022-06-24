package com.zsh.cloud.system.application.dto;

import com.zsh.cloud.common.core.domain.TreeNode;
import com.zsh.cloud.common.core.enums.StatusEnum;
import com.zsh.cloud.common.web.translate.Translate;
import com.zsh.cloud.common.web.translate.TranslateField;
import com.zsh.cloud.system.application.translate.ServiceImplTranslator;
import com.zsh.cloud.system.infrastructure.persistence.repository.MenuRepositoryImpl;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 菜单树信息DTO.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/17 16:28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "MenuTreeDTO", description = "菜单树信息")
public class MenuTreeDTO extends TreeNode<MenuTreeDTO, String> {
    
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
     * 父组织名称.
     */
    @ApiModelProperty(value = "父菜单名称")
    @Translate(translator = ServiceImplTranslator.class, dataSource = MenuRepositoryImpl.class, from = "parentId", param = "menuName")
    private String parentName;
    
    /**
     * 排序.
     */
    @ApiModelProperty(value = "排序")
    private Integer sortValue;
    
    /**
     * 状态 1启用，0禁用.
     */
    @ApiModelProperty(value = "状态")
    private Integer status;
    
    /**
     * 状态 1启用 0禁用.
     */
    @ApiModelProperty(value = "状态")
    @Translate(dataSource = StatusEnum.class, from = "status")
    private String statusDesc;
    
    /**
     * 子集合.
     */
    @TranslateField
    private List<MenuTreeDTO> childList;
}
