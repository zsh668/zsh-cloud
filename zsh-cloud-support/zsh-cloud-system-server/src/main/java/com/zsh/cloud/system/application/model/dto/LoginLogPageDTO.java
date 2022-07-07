package com.zsh.cloud.system.application.model.dto;

import com.zsh.cloud.common.core.dto.DTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 登录日志分页信息DTO.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/7 10:13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "LoginLogPageDTO", description = "登录日志分页信息")
public class LoginLogPageDTO extends DTO {
    
    /**
     * id.
     */
    @ApiModelProperty(value = "id")
    private String id;
    
    /**
     * 操作IP
     */
    @ApiModelProperty(value = "操作IP")
    private String requestIp;
    
    /**
     * 登录人账号
     */
    @ApiModelProperty(value = "登录人账号")
    private String account;
    
    /**
     * 登录人姓名
     */
    @ApiModelProperty(value = "登录人姓名")
    private String userName;
    
    /**
     * 登录描述
     */
    @ApiModelProperty(value = "登录描述")
    private String description;
    
    /**
     * 登录时间
     */
    @ApiModelProperty(value = "登录时间")
    private LocalDateTime loginTime;
    
    /**
     * 浏览器名称
     */
    @ApiModelProperty(value = "浏览器名称")
    private String browser;
    
    /**
     * 浏览器版本
     */
    @ApiModelProperty(value = "浏览器版本")
    private String browserVersion;
    
    /**
     * 操作系统
     */
    @ApiModelProperty(value = "操作系统")
    private String operatingSystem;
    
    /**
     * 登录地点
     */
    @ApiModelProperty(value = "登录地点")
    private String location;
}
