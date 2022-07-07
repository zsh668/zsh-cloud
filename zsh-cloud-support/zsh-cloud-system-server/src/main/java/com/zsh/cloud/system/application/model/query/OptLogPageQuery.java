package com.zsh.cloud.system.application.model.query;

import com.zsh.cloud.common.core.dto.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 操作日志分页Query.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/7 10:13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("操作日志分页查询对象")
public class OptLogPageQuery extends PageQuery {
    
    /**
     * 登录人姓名.
     */
    @ApiModelProperty(value = "登录人姓名")
    private String userName;
    
    /**
     * 操作IP.
     */
    @ApiModelProperty(value = "操作IP")
    private String requestIp;
    
    /**
     * 日志类型.
     */
    @ApiModelProperty(value = "日志类型")
    private String type;
    
    /**
     * 请求类型 #HttpMethod{GET:GET请求;POST:POST请求;PUT:PUT请求;DELETE:DELETE请求;PATCH:PATCH请求;TRACE:TRACE请求;HEAD:HEAD请求;OPTIONS:OPTIONS请求;}
     */
    @ApiModelProperty(value = "请求类型")
    private String httpMethod;
    
    /**
     * 开始操作时间.
     */
    @ApiModelProperty(value = "开始操作时间")
    private LocalDateTime startOptTime;
    
    /**
     * 结束操作时间.
     */
    @ApiModelProperty(value = "结束操作时间")
    private LocalDateTime endOptTime;
}
