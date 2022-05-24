package com.zsh.cloud.system.application.assembler;

import com.zsh.cloud.common.core.domain.IDict;
import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.common.core.enums.StatusEnum;
import com.zsh.cloud.common.tenant.contex.TenantContext;
import com.zsh.cloud.system.application.command.UserCreateCommand;
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
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getUserId() == null ? "" : user.getUserId().getId());
        userDTO.setAccount(user.getAccount() == null ? "" : user.getAccount().getAccount());
        userDTO.setUserName(user.getUserName() == null ? "" : user.getUserName().getName());
        userDTO.setEmail(user.getEmail() == null ? "" : user.getEmail().getEmail());
        userDTO.setMobile(user.getMobile() == null ? "" : user.getMobile().getMobile());
        userDTO.setGender(user.getGender() == null ? null : user.getGender().getCode());
        userDTO.setStatus(user.getStatus() == null ? null : user.getStatus().getCode());
        userDTO.setOrgId(user.getOrgId() == null ? "" : user.getOrgId().getId());
        userDTO.setStationId(user.getStationId() == null ? "" : user.getStationId().getId());
        userDTO.setAvatar(user.getAvatar());
        userDTO.setWorkDescribe(user.getWorkDescribe());
        userDTO.setPasswordExpireTime(user.getPasswordExpireTime());
        userDTO.setLastLoginTime(user.getLastLoginTime());
        userDTO.setSuperior(user.getSuperior() == null ? "" : user.getSuperior().getId());
        List<String> roleIdList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(user.getRoleIds())) {
            user.getRoleIds().forEach(roleId -> {
                roleIdList.add(roleId.getId());
            });
        }
        userDTO.setRoleIdList(roleIdList);
        List<String> roleNameList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(user.getRoleNames())) {
            user.getRoleNames().forEach(roleName -> {
                roleNameList.add(roleName.getName());
            });
        }
        userDTO.setRoleNameList(roleNameList);
        List<String> groupsNameList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(user.getUserGroupsNames())) {
            user.getUserGroupsNames().forEach(groupName -> {
                groupsNameList.add(groupName.getName());
            });
        }
        userDTO.setUserGroupsNames(groupsNameList);
        return userDTO;
    }
    
    /**
     * 转换.
     *
     * @param userCommand
     * @return
     */
    default User toUser(UserCreateCommand userCommand, List<RoleId> roleIdList) {
        String superior = userCommand.getSuperior();
        if (StringUtils.isBlank(superior)) {
            superior = "0";
        }
        // 默认 1 年 有效
        LocalDateTime passwordExpireTime = LocalDateTime.now().plusYears(1);
        return User.builder().account(new Account(userCommand.getAccount()))
                .userName(new UserName(userCommand.getUserName())).mobile(new Mobile(userCommand.getMobile()))
                .email(new Email(userCommand.getEmail())).password(Password.create(Password.DEFAULT))
                .gender(IDict.getByCode(GenderEnum.class, userCommand.getGender()))
                .status(IDict.getByCode(StatusEnum.class, userCommand.getStatus())).superior(new UserId(superior))
                .passwordExpireTime(passwordExpireTime).avatar(userCommand.getAvatar())
                .workDescribe(userCommand.getWorkDescribe()).orgId(new OrgId(userCommand.getOrgId()))
                .stationId(new StationId(userCommand.getStationId()))
                .tenantId(new TenantId(TenantContext.getTenantId())).roleIds(roleIdList).build();
    }
}
