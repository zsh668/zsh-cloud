package com.zsh.cloud.system.application.assembler;

import com.zsh.cloud.system.application.model.command.UserRoleCommand;
import com.zsh.cloud.system.domain.model.role.RoleId;
import com.zsh.cloud.system.domain.model.user.UserId;
import com.zsh.cloud.system.domain.model.userrole.UserRole;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户角色Assembler.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/11 11:01
 */
@Mapper(componentModel = "spring")
public interface UserRoleDtoAssembler {
    
    /**
     * 转换.
     *
     * @param command
     * @return
     */
    default UserRole toUser(UserRoleCommand command) {
        List<RoleId> roleIdList = new ArrayList<>();
        if (command.getRoleIds() != null) {
            command.getRoleIds().forEach(roleId -> {
                roleIdList.add(new RoleId(roleId));
            });
        }
        return new UserRole(new UserId(command.getUserId()), roleIdList);
    }
}
