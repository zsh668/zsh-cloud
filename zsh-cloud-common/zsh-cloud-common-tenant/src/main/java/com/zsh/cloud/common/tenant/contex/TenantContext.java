package com.zsh.cloud.common.tenant.contex;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
 * 多租户上下文.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/3/24 14:35
 */
public class TenantContext {
    
    /**
     * 当前租户编号
     */
    private static final ThreadLocal<String> TENANT_ID = new TransmittableThreadLocal<>();
    
    /**
     * 获得租户编号。
     *
     * @return 租户编号
     */
    public static String getTenantId() {
        return TENANT_ID.get();
    }
    
    public static void setTenantId(String tenantId) {
        TENANT_ID.set(tenantId);
    }
    
    public static void clear() {
        TENANT_ID.remove();
    }
}
