package com.zsh.cloud.system.application.model.command;

import com.zsh.cloud.common.core.dto.Command;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 * 创建资源Command.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/17 16:32
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "创建资源", description = "创建资源")
public class ResourceCreateCommand extends Command {
    
    /**
     * 资源名称.
     */
    @ApiModelProperty(value = "资源名称")
    @NotEmpty(message = "资源名称不能为空")
    @Length(max = 50, message = "资源名称长度不能超过50")
    private String resourceName;
    
    /**
     * 资源编码. 规则： 链接： 数据列： 按钮：
     */
    @ApiModelProperty(value = "资源编码")
    @NotEmpty(message = "资源编码不能为空")
    @Length(max = 100, message = "资源编码长度不能超过100")
    private String resourceCode;
    
    /**
     * 菜单ID.
     */
    @ApiModelProperty(value = "菜单ID")
    @NotEmpty(message = "菜单ID不能为空")
    private String menuId;
    
    /**
     * 请求方式.
     */
    @ApiModelProperty(value = "请求方式")
    @NotEmpty(message = "请求方式不能为空")
    @Length(max = 10, message = "请求方式长度不能超过10")
    private String method;
    
    /**
     * 请求地址.
     */
    @ApiModelProperty(value = "接口地址")
    @NotEmpty(message = "接口地址不能为空")
    @Length(max = 200, message = "接口地址长度不能超过200")
    private String url;
    
    /**
     * 接口描述.
     */
    @ApiModelProperty(value = "接口描述")
    @Length(max = 255, message = "接口描述长度不能超过255")
    private String describe;
}
