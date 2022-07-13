package com.zsh.cloud.system.application.assembler;

import com.zsh.cloud.system.application.model.command.GroupUserCommand;
import com.zsh.cloud.system.domain.model.groupuser.GroupUser;
import com.zsh.cloud.system.domain.model.user.UserId;
import com.zsh.cloud.system.domain.model.usergroup.UserGroupId;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户组用户Assembler.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/11 11:01
 */
@Mapper(componentModel = "spring")
public interface GroupUserDtoAssembler {
    
    /**
     * 转换.
     *
     * @param command
     * @return
     */
    default GroupUser toUser(GroupUserCommand command) {
        List<UserId> userIdList = new ArrayList<>();
        if (command.getUserIds() != null) {
            command.getUserIds().forEach(userId -> {
                userIdList.add(new UserId(userId));
            });
        }
        return new GroupUser(new UserGroupId(command.getGroupId()), userIdList);
    }
}
