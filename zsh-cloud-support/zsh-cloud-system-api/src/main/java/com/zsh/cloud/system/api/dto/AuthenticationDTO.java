package com.zsh.cloud.system.api.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * 认证信息DTO.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/24 17:44
 */
@Data
public class AuthenticationDTO implements Serializable {
    
    /**
     * 用户ID.
     */
    private String userId;
    
    /**
     * 用户名.
     */
    private String userName;
    
    /**
     * 密码.
     */
    private String password;
    
    /**
     * 状态.
     */
    private Boolean status;
    
    /**
     * 租户ID.
     */
    private String tenantId;
    
    /**
     * 权限编码.
     */
    private Set<String> permissionCodes;
}
