package com.zsh.cloud.system.application.dto;

import com.zsh.cloud.common.core.domain.TreeNode;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

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

}
