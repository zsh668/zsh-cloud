package com.zsh.cloud.common.core.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 排序字段.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/3/24 11:07
 */
@ApiModel("排序字段")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Sort implements Serializable {
    
    /**
     * 升序.
     **/
    public static final String ASC = "asc";
    
    /**
     * 降序.
     **/
    public static final String DESC = "desc";
    
    
    /**
     * 字段名称.
     **/
    @ApiModelProperty(value = "字段名称")
    private String field;
    
    /**
     * 排序方式.
     **/
    @ApiModelProperty(value = "排序方式", example = "desc")
    private String order;
}
