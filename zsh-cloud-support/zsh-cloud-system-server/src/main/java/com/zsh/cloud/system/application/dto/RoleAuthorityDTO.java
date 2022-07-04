package com.zsh.cloud.system.application.dto;

import com.zsh.cloud.common.core.dto.DTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 角色资源DTO.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/1 16:52
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "RoleAuthorityDTO", description = "角色资源")
public class RoleAuthorityDTO extends DTO {
    
    /**
     * 菜单ID集合.
     */
    private List<String> menuIds;
    
    /**
     * 资源ID集合.
     */
    private List<String> resourceIds;
}
