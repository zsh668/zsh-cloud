package com.zsh.cloud.system.application.assembler;

import com.zsh.cloud.common.core.domain.IDict;
import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.common.core.enums.StatusEnum;
import com.zsh.cloud.common.tenant.contex.TenantContext;
import com.zsh.cloud.system.application.command.UserCreateCommand;
import com.zsh.cloud.system.application.command.UserUpdateCommand;
import com.zsh.cloud.system.application.dto.UserDTO;
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

import java.time.LocalDateTime;
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
        UserDTO userDto = new UserDTO();
        userDto.setId(user.getUserId() == null ? "" : user.getUserId().getId());
        userDto.setAccount(user.getAccount() == null ? "" : user.getAccount().getAccount());
        userDto.setUserName(user.getUserName() == null ? "" : user.getUserName().getName());
        userDto.setEmail(user.getEmail() == null ? "" : user.getEmail().getEmail());
        userDto.setMobile(user.getMobile() == null ? "" : user.getMobile().getMobile());
        userDto.setGender(user.getGender() == null ? null : user.getGender().getCode());
        userDto.setStatus(user.getStatus() == null ? null : user.getStatus().getCode());
        userDto.setOrgId(user.getOrgId() == null ? "" : user.getOrgId().getId());
        userDto.setStationId(user.getStationId() == null ? "" : user.getStationId().getId());
        userDto.setAvatar(user.getAvatar());
        userDto.setWorkDescribe(user.getWorkDescribe());
        userDto.setPasswordExpireTime(user.getPasswordExpireTime());
        userDto.setLastLoginTime(user.getLastLoginTime());
        userDto.setSuperior(user.getSuperior() == null ? "" : user.getSuperior().getId());
        List<String> roleIdList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(user.getRoleIds())) {
            user.getRoleIds().forEach(roleId -> {
                roleIdList.add(roleId.getId());
            });
        }
        userDto.setRoleIdList(roleIdList);
        List<String> roleNameList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(user.getRoleNames())) {
            user.getRoleNames().forEach(roleName -> {
                roleNameList.add(roleName.getName());
            });
        }
        userDto.setRoleNameList(roleNameList);
        List<String> groupsNameList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(user.getUserGroupsNames())) {
            user.getUserGroupsNames().forEach(groupName -> {
                groupsNameList.add(groupName.getName());
            });
        }
        userDto.setUserGroupsNames(groupsNameList);
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
        return User.builder().account(new Account(userCommand.getAccount()))
                .userName(new UserName(userCommand.getUserName())).mobile(new Mobile(userCommand.getMobile()))
                .email(new Email(userCommand.getEmail())).password(Password.create(Password.DEFAULT))
                .gender(IDict.getByCode(GenderEnum.class, userCommand.getGender())).status(StatusEnum.ENABLE)
                .superior(new UserId(superior)).passwordExpireTime(User.createExpireTime())
                .avatar(userCommand.getAvatar()).workDescribe(userCommand.getWorkDescribe())
                .orgId(new OrgId(userCommand.getOrgId())).stationId(new StationId(userCommand.getStationId()))
                .tenantId(new TenantId(TenantContext.getTenantId())).roleIds(roleIdList).build();
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
        return User.builder().userId(new UserId(userCommand.getId())).userName(new UserName(userCommand.getUserName()))
                .mobile(new Mobile(userCommand.getMobile())).email(new Email(userCommand.getEmail()))
                .gender(IDict.getByCode(GenderEnum.class, userCommand.getGender())).superior(new UserId(superior))
                .avatar(userCommand.getAvatar()).workDescribe(userCommand.getWorkDescribe())
                .orgId(new OrgId(userCommand.getOrgId())).stationId(new StationId(userCommand.getStationId()))
                .roleIds(roleIdList).build();
    }
}
