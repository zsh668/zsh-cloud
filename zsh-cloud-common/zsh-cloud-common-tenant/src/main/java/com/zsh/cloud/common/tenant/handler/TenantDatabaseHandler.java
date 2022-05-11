package com.zsh.cloud.common.tenant.handler;

import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.zsh.cloud.common.tenant.properties.TenantProperties;
import com.zsh.cloud.common.tenant.contex.TenantContext;
import lombok.AllArgsConstructor;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.NullValue;
import net.sf.jsqlparser.expression.StringValue;

/**
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/3/24 14:30
 */
@AllArgsConstructor
public class TenantDatabaseHandler implements TenantLineHandler {
    
    private final TenantProperties properties;
    
    @Override
    public Expression getTenantId() {
        String tenant = TenantContext.getTenantId();
        if (tenant != null) {
            return new StringValue(tenant);
        }
        return new NullValue();
    }
    
    @Override
    public boolean ignoreTable(String tableName) {
        // 情况一，全局忽略多租户
        // 情况二，忽略多租户的表
        return TenantContext.isIgnore() || properties.getIgnoreTables().stream()
                .anyMatch((t) -> t.equalsIgnoreCase(tableName));
    }
}
