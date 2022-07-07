package com.zsh.cloud.system.api;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.common.log.annotations.SysLog;
import com.zsh.cloud.system.application.LoginLogApplicationService;
import com.zsh.cloud.system.application.LoginLogQueryService;
import com.zsh.cloud.system.application.model.command.IdsCommand;
import com.zsh.cloud.system.application.model.dto.LoginLogPageDTO;
import com.zsh.cloud.system.application.model.query.LoginLogPageQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 登录日志管理.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/7 10:03
 */
@Api(tags = "登录日志管理")
@Slf4j
@RestController
public class LoginLogController {
    
    @Autowired
    private LoginLogQueryService loginLogQueryService;
    
    @Autowired
    private LoginLogApplicationService loginLogApplicationService;
    
    /**
     * 分页查询登录日志.
     *
     * @param loginLogPageQuery
     * @return
     */
    @ApiOperation("分页查询登录日志")
    @SysLog("分页查询登录日志")
    @GetMapping("loginLogs")
    public Page<LoginLogPageDTO> page(LoginLogPageQuery loginLogPageQuery) {
        return loginLogQueryService.queryPage(loginLogPageQuery);
    }
    
    /**
     * 根据ID查询登录日志.
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "查询登录日志", notes = "查询登录日志")
    @SysLog("根据ID查询登录日志")
    @GetMapping("loginLogs/{id}")
    public LoginLogPageDTO get(@PathVariable String id) {
        return loginLogQueryService.find(id);
    }
    
    /**
     * 删除登录日志.
     *
     * @param command
     * @return
     */
    @ApiOperation("删除登录日志")
    @SysLog("删除登录日志")
    @DeleteMapping("loginLogs")
    public Boolean delete(@Valid @RequestBody IdsCommand command) {
        loginLogApplicationService.deleteBatch(command.getIds());
        return Boolean.TRUE;
    }
}
