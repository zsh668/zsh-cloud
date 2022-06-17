package com.zsh.cloud.system.application.query;

import com.zsh.cloud.common.core.dto.PageQuery;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜单分页Query.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/17 16:29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("菜单分页查询对象")
public class MenuPageQuery extends PageQuery {

}
