package com.zsh.cloud.auth.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.useragent.Browser;
import cn.hutool.http.useragent.OS;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.zsh.cloud.common.core.util.AddressUtil;
import com.zsh.cloud.system.api.dto.LoginLogDTO;
import com.zsh.cloud.system.api.dubbo.LoginLogDubboService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * 登录日志.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/29 10:19
 */
@Slf4j
@RestController
@RequestMapping("loginLog")
@Api(value = "LoginLog", tags = "登录日志")
public class LoginLogController {
    
    @DubboReference
    private LoginLogDubboService loginLogDubboService;
    
    /**
     * 新增登录日志.
     *
     * @param account
     * @param description
     * @param request
     * @return
     */
    @ApiOperation(value = "新增登录日志", notes = "新增登录日志不为空的字段")
    @GetMapping("/login/{account}")
    public Boolean save(@NotBlank(message = "用户名不能为为空") @PathVariable String account,
            @RequestParam(required = false, defaultValue = "登陆成功") String description, HttpServletRequest request) {
        LoginLogDTO loginLog = getLoginLogDTO(request);
        loginLog.setAccount(account);
        loginLog.setDescription(description);
        loginLogDubboService.save(loginLog);
        return Boolean.TRUE;
    }
    
    @NotNull
    private LoginLogDTO getLoginLogDTO(HttpServletRequest request) {
        String ua = StrUtil.sub(request.getHeader("user-agent"), 0, 500);
        UserAgent userAgent = UserAgentUtil.parse(ua);
        Browser browser = userAgent.getBrowser();
        String name = browser.getName();
        String browserVersion = browser.getVersion(ua);
        OS os = userAgent.getOs();
        String osVersion = os.getVersion(ua);
        String ip = ServletUtil.getClientIP(request);
        String location = AddressUtil.getRegion(ip);
        LoginLogDTO loginLog = new LoginLogDTO();
        loginLog.setLoginTime(LocalDateTime.now()).setUa(ua).setBrowser(name).setBrowserVersion(browserVersion)
                .setOperatingSystem(osVersion).setRequestIp(ip).setLocation(location);
        return loginLog;
    }
}
