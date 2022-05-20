package com.zsh.cloud.system.domain.model.user;

import com.zsh.cloud.common.core.domain.Entity;
import com.zsh.cloud.common.core.enums.StatusEnum;
import com.zsh.cloud.common.core.exception.ServiceException;
import com.zsh.cloud.common.core.exception.code.enums.ServiceErrorCode;
import com.zsh.cloud.system.domain.model.org.OrgId;
import com.zsh.cloud.system.domain.model.role.RoleId;
import com.zsh.cloud.system.domain.model.role.RoleName;
import com.zsh.cloud.system.domain.model.station.StationId;
import com.zsh.cloud.system.domain.model.tenant.TenantId;
import com.zsh.cloud.system.domain.model.usergroup.UserGroupName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.Validate;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/25 11:47
 */
@Data
@Builder
public class User implements Entity<User> {
    
    /**
     * 用户ID.
     */
    private UserId userId;
    
    /**
     * 账号.
     */
    private final Account account;
    
    /**
     * 用户名.
     */
    private final UserName userName;
    
    /**
     * 手机号.
     */
    private final Mobile mobile;
    
    /**
     * 邮箱.
     */
    private final Email email;
    
    /**
     * 密码.
     */
    private Password password;
    
    /**
     * 性别。
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
     * 角色Id列表.
     */
    private final List<RoleId> roleIds;
    
    /**
     * 角色名称列表.
     */
    private List<RoleName> roleNames;
    
    /**
     * 用户组名称列表.
     */
    private final List<UserGroupName> userGroupsNames;
    
    /**
     * 是否有效.
     *
     * @return
     */
    public void isEnable() {
        Validate.isTrue(StatusEnum.ENABLE == status, "用户被禁用，请联系管理员！");
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
    
    /**
     * 密码是否过期.
     *
     * @return
     */
    public void checkPasswordExpireTime() {
        if (this.passwordExpireTime != null) {
            Validate.isTrue(LocalDateTime.now().isBefore(this.passwordExpireTime), "用户密码已过期，请修改密码或者联系管理员重置!");
        }
    }
    
    @Override
    public boolean sameIdentityAs(User other) {
        return other != null && userId.sameValueAs(other.userId);
    }
}
