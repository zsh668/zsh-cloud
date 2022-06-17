package com.zsh.cloud.system.application.dto;

import com.zsh.cloud.common.core.dto.DTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 菜单.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/17 16:30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "MenuDTO", description = "菜单信息")
public class MenuDTO extends DTO {

}
