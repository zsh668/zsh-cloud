package com.zsh.cloud.system.application.impl;

import com.zsh.cloud.common.mybatis.datascope.domain.DataPermission;
import com.zsh.cloud.common.mybatis.core.query.LbqwExt;
import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.system.application.UserQueryService;
import com.zsh.cloud.system.application.assembler.UserDTOAssembler;
import com.zsh.cloud.system.application.dto.UserDTO;
import com.zsh.cloud.system.application.query.UserPageQuery;
import com.zsh.cloud.system.domain.model.user.User;
import com.zsh.cloud.system.domain.model.user.UserId;
import com.zsh.cloud.system.domain.model.user.UserRepository;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysUserDO;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户查询服务实现类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/29 14:45
 */
@Service
public class UserQueryServiceImpl implements UserQueryService {
    
    @Autowired
    private SysUserMapper sysUserMapper;
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public Page<SysUserDO> queryPage(UserPageQuery query) {
        Set<String> orgIds = new HashSet<>();
        orgIds.add(query.getOrgId());
        Page<SysUserDO> page = sysUserMapper.selectPage(query, orgIds);
        return page;
    }
    
    @Override
    public UserDTO find(String userId) {
        User user = userRepository.find(new UserId(userId));
        UserDTO userDTO = UserDTOAssembler.fromUser(user);
        //        SysTenantDO tenantDO = sysTenantMapper.selectById(user.getTenantId());
        //        userDTO.setTenantName(tenantDO.getTenantName());
        //        userDTO.setPermissionCodes(permissionQueryService.getPermissionCodes(userId));
        //        userDTO.setPermissionIds(permissionQueryService.getPermissionIds(userId));
        //        userDTO.setTenants(getUserTenants(userId));
        return userDTO;
    }
    
    @Override
    public DataPermission getDataScopeById(String userId) {
        DataPermission dataPermission = new DataPermission();
        dataPermission.setOrgIds(Arrays.asList("2", "3", "4"));
        dataPermission.setDsType(2);
        return dataPermission;
    }
}
