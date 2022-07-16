package com.zsh.cloud.system.domain.model.user;

import com.zsh.cloud.common.core.domain.Entity;
import com.zsh.cloud.common.core.enums.StatusEnum;
import com.zsh.cloud.common.core.exception.code.enums.ServiceErrorCode;
import com.zsh.cloud.common.core.util.ServiceAssert;
import com.zsh.cloud.system.domain.model.org.OrgId;
import com.zsh.cloud.system.domain.model.role.RoleId;
import com.zsh.cloud.system.domain.model.role.RoleName;
import com.zsh.cloud.system.domain.model.station.StationId;
import com.zsh.cloud.system.domain.model.tenant.TenantId;
import com.zsh.cloud.system.domain.model.usergroup.UserGroupName;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/25 11:47
 */
@Getter
public class User implements Entity<User> {
    
    public static final String SUPERIOR = "-1";
    
    /**
     * 用户ID.
     */
    private UserId userId;
    
    /**
     * 账号.
     */
    private Account account;
    
    /**
     * 用户名.
     */
    private UserName userName;
    
    /**
     * 手机号.
     */
    private Mobile mobile;
    
    /**
     * 邮箱.
     */
    private Email email;
    
    /**
     * 密码.
     */
    private Password password;
    
    /**
     * 性别.
     */
    private GenderEnum gender;
    
    /**
     * 状态.
     */
    private StatusEnum status;
    
    /**
     * 上级领导.
     */
    private UserId superior;
    
    /**
     * 密码过期时间.
     */
    private LocalDateTime passwordExpireTime;
    
    /**
     * 最后登录时间.
     */
    private LocalDateTime lastLoginTime;
    
    /**
     * 头像.
     */
    private String avatar;
    
    /**
     * 工作描述.
     */
    private String workDescribe;
    
    /**
     * 组织ID.
     */
    private OrgId orgId;
    
    /**
     * 岗位ID.
     */
    private StationId stationId;
    
    /**
     * 当前租户.
     */
    private TenantId tenantId;
    
    /**
     * 创建时间.
     */
    private LocalDateTime createdTime;
    
    /**
     * 更新时间.
     */
    private LocalDateTime updatedTime;
    
    /**
     * 角色Id列表.
     */
    private List<RoleId> roleIds;
    
    /**
     * 角色名称列表.
     */
    private List<RoleName> roleNames;
    
    /**
     * 用户组名称列表.
     */
    private List<UserGroupName> userGroupsNames;
    
    /**
     * 是否更新用户角色
     */
    private Boolean updatedUserRole = Boolean.TRUE;
    
    /**
     * 创建.
     *
     * @param account
     * @param userName
     * @param mobile
     * @param email
     * @param password
     * @param gender
     * @param superior
     * @param passwordExpireTime
     * @param avatar
     * @param workDescribe
     * @param orgId
     * @param stationId
     * @param tenantId
     * @param roleIds
     */
    public User(Account account, UserName userName, Mobile mobile, Email email, Password password, GenderEnum gender,
            UserId superior, LocalDateTime passwordExpireTime, String avatar, String workDescribe, OrgId orgId,
            StationId stationId, TenantId tenantId, List<RoleId> roleIds) {
        this.account = account;
        this.userName = userName;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.status = StatusEnum.ENABLE;
        this.superior = superior;
        this.passwordExpireTime = passwordExpireTime;
        this.avatar = avatar;
        this.workDescribe = workDescribe;
        this.orgId = orgId;
        this.stationId = stationId;
        this.tenantId = tenantId;
        this.roleIds = roleIds;
    }
    
    /**
     * 更新.
     *
     * @param userId
     * @param userName
     * @param mobile
     * @param email
     * @param gender
     * @param superior
     * @param avatar
     * @param workDescribe
     * @param orgId
     * @param stationId
     * @param roleIds
     */
    public User(UserId userId, UserName userName, Mobile mobile, Email email, GenderEnum gender, UserId superior,
            String avatar, String workDescribe, OrgId orgId, StationId stationId, List<RoleId> roleIds) {
        this.userId = userId;
        this.userName = userName;
        this.mobile = mobile;
        this.email = email;
        this.gender = gender;
        this.superior = superior;
        this.avatar = avatar;
        this.workDescribe = workDescribe;
        this.orgId = orgId;
        this.stationId = stationId;
        this.roleIds = roleIds;
    }
    
    /**
     * 查询.
     *
     * @param userId
     * @param account
     * @param userName
     * @param mobile
     * @param email
     * @param password
     * @param gender
     * @param status
     * @param superior
     * @param avatar
     * @param passwordExpireTime
     * @param lastLoginTime
     * @param workDescribe
     * @param orgId
     * @param stationId
     * @param tenantId
     * @param createdTime
     * @param updatedTime
     * @param roleIds
     * @param roleNames
     * @param userGroupsNames
     */
    public User(UserId userId, Account account, UserName userName, Mobile mobile, Email email, Password password,
            GenderEnum gender, StatusEnum status, UserId superior, String avatar, LocalDateTime passwordExpireTime,
            LocalDateTime lastLoginTime, String workDescribe, OrgId orgId, StationId stationId, TenantId tenantId,
            LocalDateTime createdTime, LocalDateTime updatedTime, List<RoleId> roleIds, List<RoleName> roleNames,
            List<UserGroupName> userGroupsNames) {
        this.userId = userId;
        this.account = account;
        this.userName = userName;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.status = status;
        this.superior = superior;
        this.avatar = avatar;
        this.passwordExpireTime = passwordExpireTime;
        this.lastLoginTime = lastLoginTime;
        this.workDescribe = workDescribe;
        this.orgId = orgId;
        this.stationId = stationId;
        this.tenantId = tenantId;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.roleIds = roleIds;
        this.roleNames = roleNames;
        this.userGroupsNames = userGroupsNames;
    }
    
    public User(UserId userId, List<RoleId> roleIds) {
        this.userId = userId;
        this.roleIds = roleIds;
    }
    
    public User(UserId userId, UserName userName, Mobile mobile, Email email, GenderEnum gender, String avatar,
            String workDescribe) {
        this.userId = userId;
        this.userName = userName;
        this.mobile = mobile;
        this.email = email;
        this.gender = gender;
        this.avatar = avatar;
        this.workDescribe = workDescribe;
    }
    
    /**
     * 不更新.
     *
     * @return
     */
    public void notUpdated() {
        this.updatedUserRole = Boolean.FALSE;
    }
    
    /**
     * 是否有效.
     *
     * @return
     */
    public boolean isEnable() {
        return status == StatusEnum.ENABLE;
    }
    
    /**
     * 启用、禁用.
     */
    public void disable() {
        this.status = isEnable() ? StatusEnum.DISABLE : StatusEnum.ENABLE;
    }
    
    /**
     * 密码是否正确.
     *
     * @param passwordStr
     * @return
     */
    public boolean checkPassword(String passwordStr) {
        return password != null && this.password.sameValueAs(new Password(passwordStr));
    }
    
    /**
     * 修改密码.
     *
     * @param oldPasswordStr
     * @param newPasswordStr
     * @return
     */
    public void changePassword(String oldPasswordStr, String newPasswordStr) {
        ServiceAssert.isTrue(checkPassword(oldPasswordStr), ServiceErrorCode.USER_PASSWORD_ERROR.getCode(), "原密码不正确");
        this.password = Password.create(newPasswordStr);
    }
    
    /**
     * 密码是否过期.
     *
     * @return
     */
    public boolean checkPasswordExpireTime() {
        return this.passwordExpireTime != null && LocalDateTime.now().isAfter(this.passwordExpireTime);
    }
    
    /**
     * 延长密码过期时间.
     */
    public void expand() {
        this.passwordExpireTime = createExpireTime();
    }
    
    /**
     * 密码过期时间.
     */
    public static LocalDateTime createExpireTime() {
        // 默认 1 年 有效
        return LocalDateTime.now().plusYears(1);
    }
    
    @Override
    public boolean sameIdentityAs(User other) {
        return other != null && userId.sameValueAs(other.userId);
    }
}
