package com.zsh.cloud.system.application.dto;

import com.zsh.cloud.common.core.dto.DTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 资源分页信息DTO.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/17 16:38
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "ResourcePageDTO", description = "资源分页信息")
public class ResourcePageDTO extends DTO {

}
