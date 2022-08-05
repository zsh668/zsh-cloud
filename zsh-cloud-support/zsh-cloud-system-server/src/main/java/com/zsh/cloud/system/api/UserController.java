package com.zsh.cloud.system.api;

import com.alibaba.excel.EasyExcelFactory;
import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.common.core.exception.code.enums.GlobalErrorCode;
import com.zsh.cloud.common.core.util.RequestUtils;
import com.zsh.cloud.common.core.util.ServiceAssert;
import com.zsh.cloud.common.log.annotations.SysLog;
import com.zsh.cloud.common.web.excel.export.ExportExcel;
import com.zsh.cloud.common.web.excel.imports.ImportResultDTO;
import com.zsh.cloud.common.web.model.IdsCommand;
import com.zsh.cloud.common.web.translate.Translator;
import com.zsh.cloud.common.web.util.ExcelUtils;
import com.zsh.cloud.system.api.excel.UserImportListener;
import com.zsh.cloud.system.application.UserApplicationService;
import com.zsh.cloud.system.application.UserManageService;
import com.zsh.cloud.system.application.UserQueryService;
import com.zsh.cloud.system.application.model.command.CurrentUserCommand;
import com.zsh.cloud.system.application.model.command.PasswordCommand;
import com.zsh.cloud.system.application.model.command.UserCreateCommand;
import com.zsh.cloud.system.application.model.command.UserImportExcelCommand;
import com.zsh.cloud.system.application.model.command.UserRoleCommand;
import com.zsh.cloud.system.application.model.command.UserTenantCommand;
import com.zsh.cloud.system.application.model.command.UserUpdateCommand;
import com.zsh.cloud.system.application.model.dto.HierarchyDTO;
import com.zsh.cloud.system.application.model.dto.LoginDTO;
import com.zsh.cloud.system.application.model.dto.UserDTO;
import com.zsh.cloud.system.application.model.dto.UserPageDTO;
import com.zsh.cloud.system.application.model.query.UserPageQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

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
public class UserController {
    
    @Autowired
    private UserQueryService userQueryService;
    
    @Autowired
    private UserApplicationService userApplicationService;
    
    @Autowired
    private UserManageService userManageService;
    
    /**
     * 分页查询用户.
     *
     * @param userPageQuery
     * @return
     */
    @ApiOperation("分页查询用户")
    @SysLog("分页查询用户")
    @Translator
    @ExportExcel(fileName = "用户")
    @GetMapping("users")
    public Page<UserPageDTO> page(UserPageQuery userPageQuery) {
        return userQueryService.queryPage(userPageQuery);
    }
    
    /**
     * 查询用户列表.
     *
     * @param userPageQuery
     * @return
     */
    @ApiOperation("查询用户列表")
    @SysLog("查询用户列表")
    @Translator
    @GetMapping("users/list")
    public List<UserPageDTO> list(UserPageQuery userPageQuery) {
        return userQueryService.queryList(userPageQuery);
    }
    
    /**
     * 根据ID查询用户.
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "查询用户", notes = "查询用户")
    @SysLog("根据ID查询用户")
    @Translator
    @GetMapping("users/{id}")
    public UserDTO get(@PathVariable String id) {
        return userQueryService.find(id);
    }
    
    /**
     * 保存用户.
     *
     * @param userCommand
     * @return
     */
    @ApiOperation("保存用户")
    @SysLog("保存用户")
    @PostMapping("users")
    public Boolean save(@Valid @RequestBody UserCreateCommand userCommand) {
        userApplicationService.save(userCommand);
        return Boolean.TRUE;
    }
    
    /**
     * 修改用户.
     *
     * @param userCommand
     * @return
     */
    @ApiOperation("修改用户")
    @SysLog("修改用户")
    @PutMapping("users")
    public Boolean update(@Valid @RequestBody UserUpdateCommand userCommand) {
        userApplicationService.update(userCommand);
        return Boolean.TRUE;
    }
    
    /**
     * 删除用户.
     *
     * @param command
     * @return
     */
    @ApiOperation("删除用户")
    @SysLog("删除用户")
    @DeleteMapping("users")
    public Boolean delete(@Valid @RequestBody IdsCommand command) {
        userApplicationService.deleteBatch(command.getIds());
        return Boolean.TRUE;
    }
    
    /**
     * 开启、禁用用户.
     *
     * @param id
     * @return
     */
    @ApiOperation("开启、禁用用户")
    @SysLog("开启、禁用用户")
    @PutMapping("users/disable/{id}")
    public Boolean disable(@PathVariable String id) {
        userApplicationService.disable(id);
        return Boolean.TRUE;
    }
    
    /**
     * 修改登录用户密码.
     *
     * @param passwordCommand
     * @return
     */
    @ApiOperation("修改密码")
    @SysLog("修改密码")
    @PutMapping("users/password")
    public Boolean changePassword(@Valid @RequestBody PasswordCommand passwordCommand) {
        passwordCommand.setUserId(RequestUtils.getUserId());
        userApplicationService.changePassword(passwordCommand);
        return Boolean.TRUE;
    }
    
    /**
     * 重置密码.
     *
     * @param command
     * @return
     */
    @ApiOperation(value = "重置密码", notes = "重置密码")
    @SysLog("重置密码")
    @PutMapping("users/reset")
    public Boolean resetPassword(@Valid @RequestBody IdsCommand command) {
        userApplicationService.resetPassword(command.getIds());
        return Boolean.TRUE;
    }
    
    /**
     * 分配角色.
     *
     * @param command
     * @return
     */
    @ApiOperation(value = "分配角色", notes = "分配角色")
    @SysLog("分配角色")
    @PutMapping("users/role")
    public Boolean updateRole(@Valid @RequestBody UserRoleCommand command) {
        userApplicationService.updateRole(command);
        return Boolean.TRUE;
    }
    
    /**
     * 分配租户.
     *
     * @param command
     * @return
     */
    @ApiOperation(value = "分配租户", notes = "分配租户")
    @SysLog("分配租户")
    @PutMapping("users/tenant")
    public Boolean updateTenant(@Valid @RequestBody UserTenantCommand command) {
        userApplicationService.updateTenant(command);
        return Boolean.TRUE;
    }
    
    @ApiOperation(value = "查询用户上下级结构", notes = "查询用户上下级结构")
    @GetMapping("users/hierarchy/{id}")
    @Translator
    @SysLog("查询用户上下级结构")
    public HierarchyDTO hierarchy(@PathVariable String id) {
        return userQueryService.findHierarchy(id);
    }
    
    /**
     * 修改当前登录信息
     *
     * @return
     */
    @ApiOperation(value = "修改当前登录信息", notes = "修改当前登录信息")
    @SysLog("修改当前登录信息")
    @PutMapping("users/current")
    public Boolean updateCurrentUser(@Valid @RequestBody CurrentUserCommand userCommand) {
        userCommand.setUserId(RequestUtils.getUserId());
        userApplicationService.update(userCommand);
        return Boolean.TRUE;
    }
    
    /**
     * 获取当前登录信息
     *
     * @return
     */
    @ApiOperation(value = "获取当前登录信息", notes = "获取当前登录信息")
    @SysLog("获取当前登录信息")
    @GetMapping("users/current")
    public LoginDTO currentLogin() {
        return userQueryService.current();
    }
    
    /**
     * 下载用户导入模板.
     */
    @ApiOperation("下载用户导入模板")
    @SysLog("下载用户导入模板")
    @GetMapping("users/importTemplateFile")
    public void getImportTemplateFile() {
        ExcelUtils.renderImportFile("用户导入模板.xlsx", UserImportExcelCommand.class);
    }
    
    /**
     * 导入.
     *
     * @param file
     */
    @PostMapping("users/importExcel")
    @ApiOperation("导入")
    public ImportResultDTO importExcel(@RequestParam(value = "file") MultipartFile file) throws IOException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("导入excel-" + System.currentTimeMillis());
        ServiceAssert.notTrue(file.isEmpty(), GlobalErrorCode.BAD_REQUEST.getCode(), "导入内容为空");
        ImportResultDTO importResult = new ImportResultDTO();
        EasyExcelFactory.read(file.getInputStream(), UserImportExcelCommand.class,
                new UserImportListener(importResult, userManageService)).sheet().doRead();
        stopWatch.stop();
        log.info("导入excel 用时 :{}", stopWatch.getLastTaskTimeMillis());
        return importResult;
    }
}
