package com.zsh.cloud.system.application.model.dto;

import com.zsh.cloud.common.core.dto.DTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 操作日志分页信息DTO.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/7 10:19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "OptLogPageDTO", description = "操作日志分页信息")
public class OptLogPageDTO extends DTO {
    
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private String id;
    
    /**
     * 操作IP
     */
    @ApiModelProperty(value = "操作IP")
    private String requestIp;
    
    /**
     * 日志类型.
     */
    @ApiModelProperty(value = "日志类型")
    private String type;
    
    /**
     * 操作人
     */
    @ApiModelProperty(value = "操作人")
    private String userName;
    
    /**
     * 操作描述
     */
    @ApiModelProperty(value = "操作描述")
    private String description;
    
    /**
     * 类路径
     */
    @ApiModelProperty(value = "类路径")
    private String classPath;
    
    /**
     * 请求类型
     */
    @ApiModelProperty(value = "请求类型")
    private String actionMethod;
    
    /**
     * 请求地址
     */
    @ApiModelProperty(value = "请求地址")
    private String requestUri;
    
    /**
     * 请求类型 #HttpMethod{GET:GET请求;POST:POST请求;PUT:PUT请求;DELETE:DELETE请求;PATCH:PATCH请求;TRACE:TRACE请求;HEAD:HEAD请求;OPTIONS:OPTIONS请求;}
     */
    @ApiModelProperty(value = "请求类型")
    private String httpMethod;
    
    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    private LocalDateTime startTime;
    
    /**
     * 消耗时间
     */
    @ApiModelProperty(value = "消耗时间")
    private Long consumingTime;
    
    /**
     * 浏览器
     */
    @ApiModelProperty(value = "浏览器")
    private String ua;
}
