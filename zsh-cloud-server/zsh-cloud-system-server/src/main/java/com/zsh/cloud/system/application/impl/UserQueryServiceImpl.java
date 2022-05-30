package com.zsh.cloud.system.application.impl;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.common.mybatis.datascope.domain.DataPermission;
import com.zsh.cloud.system.application.UserQueryService;
import com.zsh.cloud.system.application.assembler.UserDtoAssembler;
import com.zsh.cloud.system.application.dto.UserDTO;
import com.zsh.cloud.system.application.dto.UserPageDTO;
import com.zsh.cloud.system.application.query.UserPageQuery;
import com.zsh.cloud.system.domain.model.user.User;
import com.zsh.cloud.system.domain.model.user.UserId;
import com.zsh.cloud.system.domain.model.user.UserRepository;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysUserDO;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
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
    
    @Autowired
    private UserDtoAssembler userDtoAssembler;
    
    @Override
    public Page<UserPageDTO> queryPage(UserPageQuery query) {
        Page<SysUserDO> page = sysUserMapper.selectPage(query, getOrgCondition(query.getOrgId()));
        return userDtoAssembler.toDto(page);
    }
    
    @Override
    public UserDTO find(String userId) {
        User user = userRepository.find(new UserId(userId));
        return userDtoAssembler.fromUser(user);
    }
    
    @Override
    public DataPermission getDataScopeById(String userId) {
        DataPermission dataPermission = new DataPermission();
        dataPermission.setOrgIds(Arrays.asList("2", "3", "4"));
        dataPermission.setDsType(2);
        return dataPermission;
    }
    
    /**
     * 获得组织条件：查询指定组织的子组织编号们，包括自身.
     *
     * @param orgId 组织编号
     * @return 组织编号集合
     */
    private Set<String> getOrgCondition(String orgId) {
        if (orgId == null) {
            return Collections.emptySet();
        }
        //Set<Long> deptIds = convertSet(deptService.getDeptsByParentIdFromCache(deptId, true), DeptDO::getId);
        Set<String> deptIds = new HashSet<>();
        // 包括自身
        deptIds.add(orgId);
        return deptIds;
    }
}
