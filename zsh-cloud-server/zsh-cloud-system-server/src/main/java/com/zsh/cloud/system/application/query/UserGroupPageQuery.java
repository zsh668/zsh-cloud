package com.zsh.cloud.system.application.query;

import com.zsh.cloud.common.core.dto.PageQuery;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户组分页Query.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/31 10:53
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("用户组分页对象")
public class UserGroupPageQuery extends PageQuery {
    
}
