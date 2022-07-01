package com.zsh.cloud.system.application.assembler;

import com.zsh.cloud.common.core.domain.IDict;
import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.common.tenant.contex.TenantContext;
import com.zsh.cloud.system.application.command.UserCreateCommand;
import com.zsh.cloud.system.application.command.UserUpdateCommand;
import com.zsh.cloud.system.application.dto.UserDTO;
import com.zsh.cloud.system.application.dto.UserInfoDTO;
import com.zsh.cloud.system.application.dto.UserPageDTO;
import com.zsh.cloud.system.domain.model.org.OrgId;
import com.zsh.cloud.system.domain.model.role.RoleId;
import com.zsh.cloud.system.domain.model.station.StationId;
import com.zsh.cloud.system.domain.model.tenant.TenantId;
import com.zsh.cloud.system.domain.model.user.Account;
import com.zsh.cloud.system.domain.model.user.Email;
import com.zsh.cloud.system.domain.model.user.GenderEnum;
import com.zsh.cloud.system.domain.model.user.Mobile;
import com.zsh.cloud.system.domain.model.user.Password;
import com.zsh.cloud.system.domain.model.user.User;
import com.zsh.cloud.system.domain.model.user.UserId;
import com.zsh.cloud.system.domain.model.user.UserName;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysUserDO;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户Assembler.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/11 11:01
 */
@Mapper(componentModel = "spring")
public interface UserDtoAssembler {
    
    /**
     * user转换.
     *
     * @param user
     * @return
     */
    UserPageDTO toDto(SysUserDO user);
    
    /**
     * 转换.
     *
     * @param user
     * @return
     */
    UserInfoDTO toDto(UserDTO user);
    
    /**
     * user转换.(UserPageDTO toDto())
     *
     * @param user
     * @return
     */
    List<UserPageDTO> toDto(List<SysUserDO> user);
    
    /**
     * user转换.(List toDto())
     *
     * @param user
     * @return
     */
    Page<UserPageDTO> toDto(Page<SysUserDO> user);
    
    /**
     * 转换.
     *
     * @param user
     * @return
     */
    default UserDTO fromUser(User user) {
        if (user == null) {
            return null;
        }
        UserDTO userDto = new UserDTO();
        userDto.setId(user.getUserId() == null ? "" : user.getUserId().getId())
                .setAccount(user.getAccount() == null ? "" : user.getAccount().getAccount())
                .setUserName(user.getUserName() == null ? "" : user.getUserName().getName())
                .setEmail(user.getEmail() == null ? "" : user.getEmail().getEmail())
                .setMobile(user.getMobile() == null ? "" : user.getMobile().getMobile())
                .setGender(user.getGender() == null ? null : user.getGender().getCode())
                .setStatus(user.getStatus() == null ? null : user.getStatus().getCode())
                .setOrgId(user.getOrgId() == null ? "" : user.getOrgId().getId())
                .setStationId(user.getStationId() == null ? "" : user.getStationId().getId())
                .setAvatar(user.getAvatar()).setWorkDescribe(user.getWorkDescribe())
                .setPasswordExpireTime(user.getPasswordExpireTime()).setLastLoginTime(user.getLastLoginTime())
                .setSuperior(user.getSuperior() == null ? "" : user.getSuperior().getId());
        List<String> roleIds = new ArrayList<>();
        if (!CollectionUtils.isEmpty(user.getRoleIds())) {
            user.getRoleIds().forEach(roleId -> {
                roleIds.add(roleId.getId());
            });
        }
        userDto.setRoleIds(roleIds);
        List<String> roleNames = new ArrayList<>();
        if (!CollectionUtils.isEmpty(user.getRoleNames())) {
            user.getRoleNames().forEach(roleName -> {
                roleNames.add(roleName.getName());
            });
        }
        userDto.setRoleNames(roleNames);
        List<String> groupsNames = new ArrayList<>();
        if (!CollectionUtils.isEmpty(user.getUserGroupsNames())) {
            user.getUserGroupsNames().forEach(groupName -> {
                groupsNames.add(groupName.getName());
            });
        }
        userDto.setUserGroupsNames(groupsNames);
        return userDto;
    }
    
    /**
     * 创建用户转换.
     *
     * @param userCommand
     * @return
     */
    default User toUser(UserCreateCommand userCommand) {
        List<RoleId> roleIdList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(userCommand.getRoleIdList())) {
            userCommand.getRoleIdList().forEach(roleId -> {
                roleIdList.add(new RoleId(roleId));
            });
        }
        String superior = userCommand.getSuperior();
        if (StringUtils.isBlank(superior)) {
            superior = "-1";
        }
        return new User(new Account(userCommand.getAccount()), new UserName(userCommand.getUserName()),
                new Mobile(userCommand.getMobile()), new Email(userCommand.getEmail()),
                Password.create(Password.DEFAULT), IDict.getByCode(GenderEnum.class, userCommand.getGender()),
                new UserId(superior), User.createExpireTime(), userCommand.getAvatar(), userCommand.getWorkDescribe(),
                new OrgId(userCommand.getOrgId()), new StationId(userCommand.getStationId()),
                new TenantId(TenantContext.getTenantId()), roleIdList);
    }
    
    /**
     * 更新用户转换.
     *
     * @param userCommand
     * @return
     */
    default User toUser(UserUpdateCommand userCommand) {
        List<RoleId> roleIdList = new ArrayList<>();
        if (userCommand.getRoleIdList() != null) {
            userCommand.getRoleIdList().forEach(roleId -> {
                roleIdList.add(new RoleId(roleId));
            });
        }
        String superior = userCommand.getSuperior();
        if (StringUtils.isBlank(superior)) {
            superior = "-1";
        }
        return new User(new UserId(userCommand.getId()), new UserName(userCommand.getUserName()),
                new Mobile(userCommand.getMobile()), new Email(userCommand.getEmail()),
                IDict.getByCode(GenderEnum.class, userCommand.getGender()), new UserId(superior),
                userCommand.getAvatar(), userCommand.getWorkDescribe(), new OrgId(userCommand.getOrgId()),
                new StationId(userCommand.getStationId()), roleIdList);
    }
}
