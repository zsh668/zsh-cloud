package com.zsh.cloud.common.tenant.interceptor;

import com.zsh.cloud.common.core.constant.CommonConstant;
import com.zsh.cloud.common.core.contex.TenantContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 租户拦截.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/3/24 15:20
 */
public class TenantHandlerInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String tenantId = request.getHeader(CommonConstant.TENANT_ID);
        if (StringUtils.isNotBlank(tenantId)) {
            TenantContext.setTenantId(tenantId);
        }
        return true;
    }
}
