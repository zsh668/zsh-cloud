package com.zsh.cloud.common.core.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 分页Query.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/3/31 16:03
 */
@ApiModel("分页参数")
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class PageQuery extends Query {
    
    private static final Long PAGE_NO = 1L;
    
    private static final Long PAGE_SIZE = 10L;
    
    /**
     * 页码.
     */
    @ApiModelProperty(value = "页码，从 1 开始", required = true, example = "1")
    @NotNull(message = "页码不能为空")
    @Min(value = 1, message = "页码最小值为 1")
    private Long current = PAGE_NO;
    
    /**
     * 每页条数.
     */
    @ApiModelProperty(value = "每页条数", required = true, example = "10")
    @NotNull(message = "每页条数不能为空")
    @Min(value = 1, message = "页码最小值为 1")
    private Long size = PAGE_SIZE;
}
