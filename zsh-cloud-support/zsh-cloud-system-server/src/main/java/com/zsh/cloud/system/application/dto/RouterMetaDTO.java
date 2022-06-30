package com.zsh.cloud.system.application.dto;

import com.zsh.cloud.common.core.dto.DTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Vue路由 Meta DTO.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/29 18:23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "RouterMetaDTO", description = "Vue路由 Meta")
public class RouterMetaDTO extends DTO {
    
    /**
     * 标题.
     */
    @ApiModelProperty(value = "标题")
    private String title;
    
    /**
     * 图标.
     */
    @ApiModelProperty(value = "图标")
    private String icon = "";
    
    /**
     * 面包屑.
     */
    @ApiModelProperty(value = "面包屑")
    private Boolean breadcrumb = true;
}
