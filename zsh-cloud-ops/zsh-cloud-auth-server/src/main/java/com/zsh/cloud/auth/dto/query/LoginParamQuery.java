package com.zsh.cloud.auth.dto.query;

import com.zsh.cloud.common.core.dto.Query;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

/**
 * 登录参数.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/27 11:51
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "LoginParamQuery", description = "登录参数")
public class LoginParamQuery extends Query {
    
    /**
     * 验证码KEY.
     */
    @ApiModelProperty(value = "验证码KEY")
    @NotEmpty(message = "验证码KEY不能为空")
    private String key;
    
    /**
     * 验证码.
     */
    @ApiModelProperty(value = "验证码")
    @NotEmpty(message = "验证码不能为空")
    private String code;
    
    /**
     * 账号.
     */
    @ApiModelProperty(value = "账号")
    @NotEmpty(message = "账号不能为空")
    private String account;
    
    /**
     * 密码.
     */
    @ApiModelProperty(value = "密码")
    @NotEmpty(message = "密码不能为空")
    private String password;
}
