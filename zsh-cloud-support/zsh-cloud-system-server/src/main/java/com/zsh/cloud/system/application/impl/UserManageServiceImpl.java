package com.zsh.cloud.system.application.impl;

import com.zsh.cloud.system.application.UserManageService;
import com.zsh.cloud.system.application.assembler.UserDtoAssembler;
import com.zsh.cloud.system.application.model.command.UserCreateCommand;
import com.zsh.cloud.system.domain.model.org.Org;
import com.zsh.cloud.system.domain.model.org.OrgName;
import com.zsh.cloud.system.domain.model.org.OrgRepository;
import com.zsh.cloud.system.domain.model.role.Role;
import com.zsh.cloud.system.domain.model.role.RoleName;
import com.zsh.cloud.system.domain.model.role.RoleRepository;
import com.zsh.cloud.system.domain.model.station.Station;
import com.zsh.cloud.system.domain.model.station.StationName;
import com.zsh.cloud.system.domain.model.station.StationRepository;
import com.zsh.cloud.system.domain.model.user.Account;
import com.zsh.cloud.system.domain.model.user.User;
import com.zsh.cloud.system.domain.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户统一服务接口实现类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/25 14:14
 */
@Service
public class UserManageServiceImpl implements UserManageService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private OrgRepository orgRepository;
    
    @Autowired
    private StationRepository stationRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private UserDtoAssembler userDtoAssembler;
    
    @Override
    public User findUser(String account) {
        List<User> users = userRepository.find(new Account(account));
        if (CollectionUtils.isEmpty(users)) {
            return null;
        }
        return users.get(0);
    }
    
    @Override
    public Org findOrg(String orgName) {
        List<Org> orgs = orgRepository.find(new OrgName(orgName));
        if (CollectionUtils.isEmpty(orgs)) {
            return null;
        }
        return orgs.get(0);
    }
    
    @Override
    public Station findStation(String stationName) {
        Station station = stationRepository.find(new StationName(stationName));
        return station;
    }
    
    @Override
    public List<Role> findRole(String[] roleNames) {
        List<RoleName> names = Arrays.stream(roleNames).map(roleName -> new RoleName(roleName))
                .collect(Collectors.toList());
        List<Role> roles = roleRepository.find(names);
        return roles;
    }
    
    @Override
    public int save(UserCreateCommand userCommand) {
        userRepository.store(userDtoAssembler.toUser(userCommand));
        return 1;
    }
}
