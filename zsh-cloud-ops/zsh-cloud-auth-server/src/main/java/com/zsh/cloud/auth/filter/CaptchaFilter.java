package com.zsh.cloud.auth.filter;

import com.zsh.cloud.auth.sevice.CaptchaService;
import com.zsh.cloud.common.core.constant.AuthConstants;
import com.zsh.cloud.common.core.domain.Result;
import com.zsh.cloud.common.core.exception.code.enums.GlobalErrorCode;
import com.zsh.cloud.common.core.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码过滤器.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/13 13:45
 */
@Slf4j
@Component
public class CaptchaFilter extends OncePerRequestFilter {
    
    @Autowired
    private CaptchaService captchaService;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        RequestMatcher matcher = new AntPathRequestMatcher(AuthConstants.OAUTH_TOKEN, HttpMethod.POST.toString());
        if (matcher.matches(request) && StringUtils.equalsIgnoreCase(request.getParameter(AuthConstants.GRANT_TYPE_KEY),
                AuthConstants.PASSWORD)) {
            String code = request.getParameter(AuthConstants.VALIDATE_CODE_CODE);
            String key = request.getParameter(AuthConstants.VALIDATE_CODE_KEY);
            if (!captchaService.validateCaptcha(key, code)) {
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.setStatus(HttpServletResponse.SC_OK);
                response.getOutputStream().write(JsonUtils.toJsonString(
                        Result.error(GlobalErrorCode.AUTHENTICATION_FAILED.getCode(), "验证码不正确")).getBytes());
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
