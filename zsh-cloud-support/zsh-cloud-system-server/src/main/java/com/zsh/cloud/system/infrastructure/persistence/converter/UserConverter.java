package com.zsh.cloud.system.infrastructure.persistence.converter;

import com.zsh.cloud.common.core.domain.IDict;
import com.zsh.cloud.common.core.enums.StatusEnum;
import com.zsh.cloud.common.core.exception.code.enums.ServiceErrorCode;
import com.zsh.cloud.common.core.util.Assert;
import com.zsh.cloud.system.domain.model.org.OrgId;
import com.zsh.cloud.system.domain.model.role.RoleId;
import com.zsh.cloud.system.domain.model.role.RoleName;
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
import com.zsh.cloud.system.domain.model.usergroup.UserGroupName;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysRoleDO;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysUserDO;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysUserGroupDO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户Converter.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/18 16:36
 */
public class UserConverter {
    
    /**
     * 转换.
     *
     * @param sysUserDO
     * @param roles
     * @param groups
     * @return
     */
    public static User toUser(SysUserDO sysUserDO, List<SysRoleDO> roles, List<SysUserGroupDO> groups) {
        if (sysUserDO == null) {
            return null;
        }
        List<RoleId> roleIds = new ArrayList<>();
        List<RoleName> roleNames = new ArrayList<>();
        if (!CollectionUtils.isEmpty(roles)) {
            roleIds = roles.stream().map(role -> new RoleId(role.getId())).collect(Collectors.toList());
            roleNames = roles.stream().map(role -> new RoleName(role.getRoleName())).collect(Collectors.toList());
        }
        List<UserGroupName> groupNames = new ArrayList<>();
        if (!CollectionUtils.isEmpty(groups)) {
            groupNames = groups.stream().map(group -> new UserGroupName(group.getGroupName()))
                    .collect(Collectors.toList());
        }
        Mobile mobile = StringUtils.isBlank(sysUserDO.getMobile()) ? null : new Mobile(sysUserDO.getMobile());
        Email email = StringUtils.isBlank(sysUserDO.getEmail()) ? null : new Email(sysUserDO.getEmail());
        TenantId tenantId = StringUtils.isBlank(sysUserDO.getTenantId()) ? null : new TenantId(sysUserDO.getTenantId());
        OrgId orgId = StringUtils.isBlank(sysUserDO.getOrgId()) ? null : new OrgId(sysUserDO.getOrgId());
        StationId stationId =
                StringUtils.isBlank(sysUserDO.getStationId()) ? null : new StationId(sysUserDO.getStationId());
        UserId superior = StringUtils.isBlank(sysUserDO.getSuperior()) ? null : new UserId(sysUserDO.getSuperior());
        
        return new User(new UserId(sysUserDO.getId()), new Account(sysUserDO.getAccount()),
                new UserName(sysUserDO.getUserName()), mobile, email, new Password(sysUserDO.getPassword()),
                IDict.getByCode(GenderEnum.class, sysUserDO.getGender()),
                IDict.getByCode(StatusEnum.class, sysUserDO.getStatus()), superior, sysUserDO.getPasswordExpireTime(),
                sysUserDO.getLastLoginTime(), orgId, stationId, tenantId, roleIds, roleNames, groupNames);
    }
    
    /**
     * 转换.
     *
     * @param user
     * @return
     */
    public static SysUserDO fromUser(User user) {
        Assert.notNull(user, ServiceErrorCode.USER_NOT_EXISTS);
        SysUserDO sysUserDO = new SysUserDO();
        sysUserDO.setId(user.getUserId() == null ? null : user.getUserId().getId());
        sysUserDO.setAccount(user.getAccount() == null ? null : user.getAccount().getAccount());
        sysUserDO.setUserName(user.getUserName() == null ? null : user.getUserName().getName());
        sysUserDO.setMobile(user.getMobile() == null ? null : user.getMobile().getMobile());
        sysUserDO.setEmail(user.getEmail() == null ? null : user.getEmail().getEmail());
        sysUserDO.setPassword(user.getPassword() == null ? null : user.getPassword().getPassword());
        sysUserDO.setGender(user.getGender() == null ? null : user.getGender().getCode());
        sysUserDO.setStatus(user.getStatus() == null ? null : user.getStatus().getCode());
        sysUserDO.setSuperior(user.getSuperior() == null ? null : user.getSuperior().getId());
        sysUserDO.setPasswordExpireTime(user.getPasswordExpireTime());
        sysUserDO.setAvatar(user.getAvatar());
        sysUserDO.setWorkDescribe(user.getWorkDescribe());
        sysUserDO.setOrgId(user.getOrgId() == null ? null : user.getOrgId().getId());
        sysUserDO.setStationId(user.getStationId() == null ? null : user.getStationId().getId());
        return sysUserDO;
    }
}
