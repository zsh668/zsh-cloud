package com.zsh.cloud.system.application.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户分页DTO.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/29 15:44
 */
@ApiModel(value = "UserPageDTO", description = "用户分页信息")
@Data
public class UserPageDTO implements Serializable {
    
    /**
     * id.
     */
    @ApiModelProperty(value = "用户ID", required = true, example = "1")
    private String id;
    
    /**
     * 账号.
     */
    private String account;
    
    /**
     * 姓名.
     */
    private String userName;
    
    /**
     * 组织ID.
     */
    private String orgId;
    
    /**
     * 岗位ID.
     */
    private String stationId;
    
    /**
     * 邮箱.
     */
    private String email;
    
    /**
     * 手机.
     */
    private String mobile;
    
    /**
     * 性别 1 男 0 女.
     */
    private Boolean sex;
    
    /**
     * 启用状态 1启用 0禁用.
     */
    private Integer status;
    
    /**
     * 头像.
     */
    private String avatar;
    
    /**
     * 工作描述 比如：市长、管理员、局长等等用于登陆展示.
     */
    private String workDescribe;
    
    /**
     * 密码.
     */
    private String password;
    
    /**
     * 最后一次输错密码时间.
     */
    private LocalDateTime passwordErrorLastTime;
    
    /**
     * 密码错误次数.
     */
    private Integer passwordErrorNum;
    
    /**
     * 密码过期时间.
     */
    private LocalDateTime passwordExpireTime;
    
    /**
     * 最后登录时间.
     */
    private LocalDateTime lastLoginTime;
    
    /**
     * 上级领导.
     */
    private String superior;
}
