package com.zsh.cloud.system.application.query;

import com.zsh.cloud.common.core.dto.PageQuery;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 资源分页Query.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/17 16:37
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("资源分页查询对象")
public class ResourcePageQuery extends PageQuery {

}
