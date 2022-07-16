package com.zsh.cloud.system.application.model.query;

import com.zsh.cloud.common.core.dto.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 登录日志分页Query.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/7 10:13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("登录日志分页查询对象")
public class LoginLogPageQuery extends PageQuery {
    
    /**
     * 登录人ID.
     */
    @ApiModelProperty(value = "登录人ID")
    private String userId;
    
    /**
     * 登录人姓名.
     */
    @ApiModelProperty(value = "登录人姓名")
    private String userName;
    
    /**
     * 登录人账号.
     */
    @ApiModelProperty(value = "登录人账号")
    private String account;
    
    /**
     * 操作IP.
     */
    @ApiModelProperty(value = "操作IP")
    private String requestIp;
    
    /**
     * 开始登录时间.
     */
    @ApiModelProperty(value = "开始登录时间")
    private LocalDateTime startLoginTime;
    
    /**
     * 结束登录时间.
     */
    @ApiModelProperty(value = "结束登录时间")
    private LocalDateTime endLoginTime;
}
