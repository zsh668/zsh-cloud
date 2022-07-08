package com.zsh.cloud.system.application.impl;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.common.core.util.CollectionUtils;
import com.zsh.cloud.common.core.util.RequestUtils;
import com.zsh.cloud.common.mybatis.datascope.domain.DataPermission;
import com.zsh.cloud.system.application.OrgQueryService;
import com.zsh.cloud.system.application.ResourceQueryService;
import com.zsh.cloud.system.application.UserQueryService;
import com.zsh.cloud.system.application.assembler.UserDtoAssembler;
import com.zsh.cloud.system.application.model.dto.HierarchyDTO;
import com.zsh.cloud.system.application.model.dto.LoginDTO;
import com.zsh.cloud.system.application.model.dto.OrgDTO;
import com.zsh.cloud.system.application.model.dto.UserDTO;
import com.zsh.cloud.system.application.model.dto.UserPageDTO;
import com.zsh.cloud.system.application.model.query.UserPageQuery;
import com.zsh.cloud.system.domain.model.user.User;
import com.zsh.cloud.system.domain.model.user.UserId;
import com.zsh.cloud.system.domain.model.user.UserRepository;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysUserDO;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysUserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

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
    
    @Autowired
    private ResourceQueryService resourceQueryService;
    
    @Autowired
    private OrgQueryService orgQueryService;
    
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
        dataPermission.setOrgIds(Arrays.asList("1", "2", "3", "4"));
        dataPermission.setDsType(2);
        return dataPermission;
    }
    
    @Override
    public LoginDTO current() {
        String userId = RequestUtils.getUserId();
        User user = userRepository.find(new UserId(userId));
        UserDTO userDTO = userDtoAssembler.fromUser(user);
        Set<String> permissionCodes = resourceQueryService.getPermissionCodes(userId);
        return new LoginDTO().setUser(userDtoAssembler.toDto(userDTO)).setPermissionCodes(permissionCodes);
    }
    
    @Override
    public HierarchyDTO findHierarchy(String userId) {
        HierarchyDTO hierarchyDTO = new HierarchyDTO();
        User user = userRepository.find(new UserId(userId));
        UserDTO userDTO = userDtoAssembler.fromUser(user);
        Stack<UserDTO> stack = new Stack<>();
        getSuperiorStack(stack, userDTO, 1);
        
        List<UserDTO> list = new ArrayList<>();
        getDownwardList(list, userDTO);
        
        buildTree(stack, list, hierarchyDTO);
        return hierarchyDTO;
    }
    
    /**
     * 获得组织条件：查询指定组织的子组织编号们，包括自身.
     *
     * @param orgId 组织编号
     * @return 组织编号集合
     */
    private Set<String> getOrgCondition(String orgId) {
        if (StringUtils.isBlank(orgId)) {
            return Collections.emptySet();
        }
        Set<String> deptIds = CollectionUtils.convertSet(orgQueryService.findChildren(orgId), OrgDTO::getId);
        // 包括自身
        deptIds.add(orgId);
        return deptIds;
    }
    
    private void buildTree(Stack<UserDTO> stack, List<UserDTO> list, HierarchyDTO hierarchyDTO) {
        if (stack == null || stack.size() == 0) {
            return;
        }
        UserDTO user = stack.pop();
        if (stack.size() == 0) {
            BeanUtils.copyProperties(user, hierarchyDTO);
            hierarchyDTO.setSelf(true);
            hierarchyDTO.setChildren(list.stream().map(item -> {
                HierarchyDTO hierarchy = new HierarchyDTO();
                BeanUtils.copyProperties(item, hierarchy);
                return hierarchy;
            }).collect(Collectors.toList()));
        } else {
            BeanUtils.copyProperties(user, hierarchyDTO);
            HierarchyDTO hierarchy = new HierarchyDTO();
            hierarchyDTO.setChildren(Arrays.asList(new HierarchyDTO[] {hierarchy}));
            
            buildTree(stack, list, hierarchy);
        }
        
    }
    
    /**
     * 获取下级的员工集合
     *
     * @param list
     * @param userDTO
     */
    private void getDownwardList(List<UserDTO> list, UserDTO userDTO) {
        List<User> users = userRepository.findBySuperior(new UserId(userDTO.getId()));
        list.addAll(users.stream().map(user -> userDtoAssembler.fromUser(user)).collect(Collectors.toList()));
    }
    
    /**
     * 获取上级关系,默认最多20级，防止内存泄露
     *
     * @param stack
     * @param userDTO
     * @param depth
     */
    private void getSuperiorStack(Stack<UserDTO> stack, UserDTO userDTO, int depth) {
        stack.add(userDTO);
        if (depth > 20) {
            return;
        }
        String superior = userDTO.getSuperior();
        if (StringUtils.isBlank(superior) || User.SUPERIOR.equals(superior)) {
            return;
        }
        User userSuperior = userRepository.find(new UserId(superior));
        UserDTO userSuperiorDTO = userDtoAssembler.fromUser(userSuperior);
        getSuperiorStack(stack, userSuperiorDTO, ++depth);
    }
}
