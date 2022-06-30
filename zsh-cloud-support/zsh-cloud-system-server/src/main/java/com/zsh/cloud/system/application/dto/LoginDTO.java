package com.zsh.cloud.system.application.dto;

import com.zsh.cloud.common.core.dto.DTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Set;

/**
 * 登录者信息.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/30 10:58
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "LoginDTO", description = "登录者信息")
public class LoginDTO extends DTO {
    
    /**
     * 用户信息.
     */
    @ApiModelProperty(value = "用户信息")
    private UserInfoDTO user;
    
    /**
     * 权限列表.
     */
    @ApiModelProperty(value = "权限列表")
    private Set<String> permissionCodes;
}
