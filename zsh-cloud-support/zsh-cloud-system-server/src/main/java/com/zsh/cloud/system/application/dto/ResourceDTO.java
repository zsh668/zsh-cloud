package com.zsh.cloud.system.application.dto;

import com.zsh.cloud.common.core.dto.DTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 资源信息DTO.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/17 16:38
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "ResourceDTO", description = "资源信息")
public class ResourceDTO extends DTO {
    
    /**
     * id
     */
    @ApiModelProperty(value = "资源ID")
    private String id;
    
    /**
     * 资源编码 规则： 链接： 数据列： 按钮：
     */
    @ApiModelProperty(value = "资源编码")
    private String resourceCode;
    
    /**
     * 接口名称
     */
    @ApiModelProperty(value = "接口名称")
    private String resourceName;
    
    /**
     * 菜单ID
     */
    @ApiModelProperty(value = "菜单ID")
    private String menuId;
    
    /**
     * 请求方式
     */
    @ApiModelProperty(value = "请求方式")
    private String method;
    
    /**
     * 请求地址
     */
    @ApiModelProperty(value = "请求地址")
    private String url;
    
    /**
     * 状态 1启用，0禁用.
     */
    @ApiModelProperty(value = "状态")
    private Boolean status;
    
    /**
     * 接口描述
     */
    @ApiModelProperty(value = "接口描述")
    private String describe;
}
