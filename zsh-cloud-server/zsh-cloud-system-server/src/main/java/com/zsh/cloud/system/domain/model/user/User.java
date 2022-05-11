package com.zsh.cloud.system.domain.model.user;

import com.zsh.cloud.common.core.domain.Entity;
import com.zsh.cloud.common.core.enums.StatusEnum;
import com.zsh.cloud.common.core.exception.ServiceException;
import com.zsh.cloud.common.core.exception.code.enums.ServiceErrorCode;
import com.zsh.cloud.system.domain.model.role.RoleId;
import com.zsh.cloud.system.domain.model.tenant.TenantId;
import lombok.Getter;

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
    
    /**
     * 用户ID.
     */
    private UserId userId;
    
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
     * 状态.
     */
    private StatusEnum status;
    
    /**
     * 当前租户.
     */
    private TenantId tenantId;
    
    /**
     * 角色Id列表.
     */
    private List<RoleId> roleIds;
    
    /**
     * 构造器.
     *
     * @param userName
     * @param mobile
     * @param email
     * @param password
     * @param roleIds
     */
    public User(UserName userName, Mobile mobile, Email email, Password password, List<RoleId> roleIds) {
        this.userName = userName;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
        this.status = StatusEnum.ENABLE;
        this.roleIds = roleIds;
    }
    
    /**
     * 构造器.
     *
     * @param userId
     * @param userName
     * @param mobile
     * @param email
     * @param password
     * @param status
     * @param tenantId
     * @param roleIds
     */
    public User(UserId userId, UserName userName, Mobile mobile, Email email, Password password, StatusEnum status,
            TenantId tenantId, List<RoleId> roleIds) {
        this.userId = userId;
        this.userName = userName;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
        this.status = status;
        this.tenantId = tenantId;
        this.roleIds = roleIds;
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
        this.status = this.status == StatusEnum.DISABLE ? StatusEnum.ENABLE : StatusEnum.DISABLE;
    }
    
    /**
     * 密码是否正确.
     *
     * @param passwordStr
     * @return
     */
    public boolean checkPassword(String passwordStr) {
        return password != null && this.password.sameValueAs(Password.create(passwordStr));
    }
    
    /**
     * 修改密码.
     *
     * @param oldPasswordStr
     * @param newPasswordStr
     * @return
     */
    public void changePassword(String oldPasswordStr, String newPasswordStr) {
        if (!checkPassword(oldPasswordStr)) {
            throw new ServiceException(ServiceErrorCode.USER_PASSWORD_ERROR.getCode(), "原密码不正确");
        }
        this.password = Password.create(newPasswordStr);
    }
    
    @Override
    public boolean sameIdentityAs(User other) {
        return other != null && userId.sameValueAs(other.userId);
    }
}
