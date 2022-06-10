package com.zsh.cloud.system.application.query;

import com.zsh.cloud.common.core.dto.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 岗位分页Query.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/10 15:50
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("岗位分页对象")
public class StationPageQuery extends PageQuery {
    
    /**
     * 岗位名称.
     */
    @ApiModelProperty(value = "岗位名称")
    private String stationName;
}
