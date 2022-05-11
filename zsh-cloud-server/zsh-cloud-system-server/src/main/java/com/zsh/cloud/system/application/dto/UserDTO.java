package com.zsh.cloud.system.application.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户信息DTO.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/29 15:26
 */
@ApiModel(value = "UserDTO", description = "用户信息")
@Data
public class UserDTO implements Serializable {
    
    /**
     * id.
     */
    @ApiModelProperty(value = "用户ID", required = true, example = "1")
    private String id;
}
