package com.zsh.cloud.system.application.assembler;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.system.application.command.UserGroupCreateCommand;
import com.zsh.cloud.system.application.command.UserGroupUpdateCommand;
import com.zsh.cloud.system.application.dto.UserGroupDTO;
import com.zsh.cloud.system.application.dto.UserGroupPageDTO;
import com.zsh.cloud.system.domain.model.role.RoleId;
import com.zsh.cloud.system.domain.model.user.UserId;
import com.zsh.cloud.system.domain.model.usergroup.UserGroup;
import com.zsh.cloud.system.domain.model.usergroup.UserGroupId;
import com.zsh.cloud.system.domain.model.usergroup.UserGroupName;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysUserGroupDO;
import org.mapstruct.Mapper;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户组Assembler.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/9 11:31
 */
@Mapper(componentModel = "spring")
public interface UserGroupDtoAssembler {
    
    /**
     * 转换.
     *
     * @param user
     * @return
     */
    UserGroupPageDTO toDto(SysUserGroupDO user);
    
    /**
     * 转换.
     *
     * @param user
     * @return
     */
    List<UserGroupPageDTO> toDto(List<SysUserGroupDO> user);
    
    /**
     * 转换.
     *
     * @param page
     * @return
     */
    Page<UserGroupPageDTO> toDto(Page<SysUserGroupDO> page);
    
    /**
     * 转换.
     *
     * @param userGroup
     * @return
     */
    default UserGroupDTO fromUser(UserGroup userGroup) {
        if (userGroup == null) {
            return null;
        }
        UserGroupDTO groupDto = new UserGroupDTO();
        groupDto.setId(userGroup.getUserGroupId() == null ? "" : userGroup.getUserGroupId().getId())
                .setGroupName(userGroup.getUserGroupName() == null ? "" : userGroup.getUserGroupName().getName())
                .setUserCount(userGroup.getUserCount())
                .setRoleId(userGroup.getRoleId() == null ? "" : userGroup.getRoleId().getId())
                .setStatus(userGroup.getStatus() == null ? null : userGroup.getStatus().getCode())
                .setDescribe(userGroup.getDescribe());
        return groupDto;
    }
    
    /**
     * 转换.
     *
     * @param userGroupCommand
     * @return
     */
    default UserGroup toUserGroup(UserGroupCreateCommand userGroupCommand) {
        List<UserId> userIdList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(userGroupCommand.getUserIdList())) {
            userGroupCommand.getUserIdList().forEach(userId -> {
                userIdList.add(new UserId(userId));
            });
        }
        return new UserGroup(new UserGroupName(userGroupCommand.getGroupName()),
                new RoleId(userGroupCommand.getRoleId()), userIdList.size(), userIdList,
                userGroupCommand.getDescribe());
    }
    
    /**
     * 转换.
     *
     * @param userGroupCommand
     * @return
     */
    default UserGroup toUserGroup(UserGroupUpdateCommand userGroupCommand) {
        List<UserId> userIdList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(userGroupCommand.getUserIdList())) {
            userGroupCommand.getUserIdList().forEach(userId -> {
                userIdList.add(new UserId(userId));
            });
        }
        return new UserGroup(new UserGroupId(userGroupCommand.getId()),
                new UserGroupName(userGroupCommand.getGroupName()), new RoleId(userGroupCommand.getRoleId()),
                userIdList.size(), null, userIdList, userGroupCommand.getDescribe());
    }
    
}