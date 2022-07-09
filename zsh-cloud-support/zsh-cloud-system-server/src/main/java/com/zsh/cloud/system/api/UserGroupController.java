package com.zsh.cloud.system.api;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.common.log.annotations.SysLog;
import com.zsh.cloud.common.web.translate.Translator;
import com.zsh.cloud.system.application.UserGroupApplicationService;
import com.zsh.cloud.system.application.UserGroupQueryService;
import com.zsh.cloud.system.application.model.command.IdsCommand;
import com.zsh.cloud.system.application.model.command.UserGroupCreateCommand;
import com.zsh.cloud.system.application.model.command.UserGroupUpdateCommand;
import com.zsh.cloud.system.application.model.dto.UserGroupDTO;
import com.zsh.cloud.system.application.model.dto.UserGroupPageDTO;
import com.zsh.cloud.system.application.model.query.UserGroupPageQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 用户组管理.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/29 14:06
 */
@Api(tags = "用户组管理")
@Slf4j
@RestController
public class UserGroupController {
    
    @Autowired
    private UserGroupQueryService userGroupQueryService;
    
    @Autowired
    private UserGroupApplicationService userGroupApplicationService;
    
    /**
     * 分页查询用户组.
     *
     * @param pageQuery
     * @return
     */
    @ApiOperation("分页查询用户组")
    @SysLog("分页查询用户组")
    @Translator
    @GetMapping("userGroups")
    public Page<UserGroupPageDTO> page(UserGroupPageQuery pageQuery) {
        return userGroupQueryService.queryPage(pageQuery);
    }
    
    /**
     * 根据ID查询用户组.
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "查询用户组", notes = "查询用户组")
    @SysLog("根据ID查询用户组")
    @Translator
    @GetMapping("userGroups/{id}")
    public UserGroupDTO get(@PathVariable String id) {
        return userGroupQueryService.find(id);
    }
    
    /**
     * 保存用户组.
     *
     * @param userGroupCommand
     * @return
     */
    @ApiOperation("保存用户组")
    @SysLog("保存用户组")
    @PostMapping("userGroups")
    public Boolean save(@Valid @RequestBody UserGroupCreateCommand userGroupCommand) {
        userGroupApplicationService.save(userGroupCommand);
        return Boolean.TRUE;
    }
    
    /**
     * 修改用户组.
     *
     * @param userGroupCommand
     * @return
     */
    @ApiOperation("修改用户组")
    @SysLog("修改用户组")
    @PutMapping("userGroups")
    public Boolean update(@Valid @RequestBody UserGroupUpdateCommand userGroupCommand) {
        userGroupApplicationService.update(userGroupCommand);
        return Boolean.TRUE;
    }
    
    /**
     * 删除用户组.
     *
     * @param command
     * @return
     */
    @ApiOperation("删除用户组")
    @SysLog("删除用户组")
    @DeleteMapping("userGroups")
    public Boolean delete(@Valid @RequestBody IdsCommand command) {
        userGroupApplicationService.deleteBatch(command.getIds());
        return Boolean.TRUE;
    }
    
    /**
     * 开启、禁用用户组.
     *
     * @param id
     * @return
     */
    @ApiOperation("开启、禁用用户组")
    @SysLog("开启、禁用用户组")
    @PutMapping("userGroups/disable/{id}")
    public Boolean disable(@PathVariable String id) {
        userGroupApplicationService.disable(id);
        return Boolean.TRUE;
    }
    
    /**
     * 分配用户.
     *
     * @param id
     * @param command
     * @return
     */
    @ApiOperation("分配用户")
    @SysLog("分配用户")
    @PutMapping("userGroups/addUser/{id}")
    public Boolean addGroupUser(@PathVariable String id, @Valid @RequestBody IdsCommand command) {
        // userGroupApplicationService.addGroupUser(command);
        return Boolean.TRUE;
    }
    
    /**
     * 增加用户.
     *
     * @param id
     * @param command
     * @return
     */
    @ApiOperation("修改角色资源")
    @SysLog("修改角色资源")
    @PutMapping("userGroups/deleteUser/{id}")
    public Boolean deleteGroupUser(@PathVariable String id, @Valid @RequestBody IdsCommand command) {
        // userGroupApplicationService.addGroupUser(command);
        return Boolean.TRUE;
    }
}
