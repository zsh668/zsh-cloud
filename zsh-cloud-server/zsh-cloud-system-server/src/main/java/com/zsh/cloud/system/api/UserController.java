package com.zsh.cloud.system.api;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.common.core.util.RequestUtils;
import com.zsh.cloud.common.core.util.ValidatorUtils;
import com.zsh.cloud.common.log.annotations.SysLog;
import com.zsh.cloud.common.web.excel.ExportExcel;
import com.zsh.cloud.common.web.group.AddGroup;
import com.zsh.cloud.common.web.group.UpdateGroup;
import com.zsh.cloud.system.application.UserApplicationService;
import com.zsh.cloud.system.application.UserQueryService;
import com.zsh.cloud.system.application.command.PasswordCommand;
import com.zsh.cloud.system.application.command.UserCommand;
import com.zsh.cloud.system.application.dto.UserDTO;
import com.zsh.cloud.system.application.query.UserPageQuery;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysUserDO;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * 用户管理.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/29 14:00
 */
@Api(tags = "用户管理")
@Slf4j
@RestController
@RequestMapping("user")
public class UserController {
    
    @Autowired
    private UserQueryService userQueryService;
    
    @Autowired
    private UserApplicationService userApplicationService;
    
    /**
     * 用户分页查询
     */
    @ApiOperation("分页查询用户")
    @ExportExcel(fileName = "用户")
    @GetMapping("page")
    public Page<SysUserDO> page(UserPageQuery userPageQuery) {
        Page<SysUserDO> page = userQueryService.queryPage(userPageQuery);
        return page;
    }
    
    /**
     * 查询用户
     */
    @ApiOperation(value = "查询用户", notes = "查询用户")
    @GetMapping("{id}")
    public UserDTO get(@PathVariable String id) {
        UserDTO userDTO = userQueryService.find(id);
        return userDTO;
    }
    
    /**
     * 保存用户
     */
    @ApiOperation("保存用户")
    @SysLog("保存用户")
    @PostMapping
    public Boolean save(@RequestBody UserCommand userCommand) {
        ValidatorUtils.validateEntity(userCommand, AddGroup.class);
        userApplicationService.save(userCommand);
        return Boolean.TRUE;
    }
    
    /**
     * 修改用户
     */
    @ApiOperation("修改用户")
    @SysLog("修改用户")
    @PutMapping
    public Boolean update(@RequestBody UserCommand userCommand) {
        ValidatorUtils.validateEntity(userCommand, UpdateGroup.class);
        userApplicationService.update(userCommand);
        return Boolean.TRUE;
    }
    
    /**
     * 删除用户
     */
    @ApiOperation("删除用户")
    @SysLog("删除用户")
    @DeleteMapping
    public Boolean delete(@RequestBody String[] userIds) {
        userApplicationService.deleteBatch(Arrays.asList(userIds));
        return Boolean.TRUE;
    }
    
    /**
     * 开启、禁用用户
     */
    @ApiOperation("开启、禁用用户")
    @SysLog("开启、禁用用户")
    @PutMapping("disable")
    public Boolean disable(String id) {
        userApplicationService.disable(id);
        return Boolean.TRUE;
    }
    
    /**
     * 修改登录用户密码
     */
    @ApiOperation("修改密码")
    @SysLog("修改密码")
    @PutMapping("password")
    public Boolean changePassword(@RequestBody PasswordCommand passwordCommand) {
        ValidatorUtils.validateEntity(passwordCommand);
        passwordCommand.setUserId(RequestUtils.getUserId());
        userApplicationService.changePassword(passwordCommand);
        return Boolean.TRUE;
    }
}
