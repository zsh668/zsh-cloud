package com.zsh.cloud.auth.domain;

import cn.hutool.core.text.StrPool;
import com.zsh.cloud.common.core.constant.AuthConstants;
import com.zsh.cloud.common.core.enums.StatusEnum;
import com.zsh.cloud.system.api.dto.AuthenticationDTO;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;

/**
 * 登录用户信息.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/13 10:44
 */
@Data
public class User implements UserDetails {
    
    private String id;
    
    private String username;
    
    private String password;
    
    private Boolean enabled;
    
    private String tenantId;
    
    private Collection<GrantedAuthority> authorities;
    
    public User(AuthenticationDTO authenticationDTO) {
        this.setId(authenticationDTO.getUserId());
        this.setUsername(authenticationDTO.getUserName());
        this.setPassword(AuthConstants.BCRYPT + authenticationDTO.getPassword());
        this.setEnabled(Objects.equals(StatusEnum.ENABLE.getCode(), authenticationDTO.getStatus()));
        this.setTenantId(authenticationDTO.getTenantId());
        this.authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(
                StringUtils.join(authenticationDTO.getPermissionCodes(), StrPool.COMMA));
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }
    
    @Override
    public String getPassword() {
        return this.password;
    }
    
    @Override
    public String getUsername() {
        return this.username;
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
