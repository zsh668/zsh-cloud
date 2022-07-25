package com.zsh.cloud.system.application;

import com.zsh.cloud.system.application.model.command.UserCreateCommand;
import com.zsh.cloud.system.domain.model.org.Org;
import com.zsh.cloud.system.domain.model.role.Role;
import com.zsh.cloud.system.domain.model.station.Station;
import com.zsh.cloud.system.domain.model.user.User;

import java.util.List;

/**
 * 用户统一服务接口.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/25 14:13
 */
public interface UserManageService {
    
    User findUser(String account);
    
    Org findOrg(String orgName);
    
    Station findStation(String stationName);
    
    List<Role> findRole(String[] roleNames);
    
    int save(UserCreateCommand userCreateCommand);
}
