package com.zsh.cloud.system.application.assembler;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.system.application.dto.UserDTO;
import com.zsh.cloud.system.application.dto.UserPageDTO;
import com.zsh.cloud.system.domain.model.user.User;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysUserDO;
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
}
