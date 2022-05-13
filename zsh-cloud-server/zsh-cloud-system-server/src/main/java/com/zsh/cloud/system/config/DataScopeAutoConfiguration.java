package com.zsh.cloud.system.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.DataPermissionInterceptor;
import com.zsh.cloud.common.core.util.SpringUtils;
import com.zsh.cloud.common.mybatis.datascope.handler.OrgDataPermissionHandler;
import com.zsh.cloud.common.mybatis.util.MyBatisUtils;
import com.zsh.cloud.system.application.UserQueryService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 数据权限自动配置.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/3/24 14:15
 */
@Configuration
public class DataScopeAutoConfiguration {
    
    /**
     * 数据权限插件.
     *
     * @param interceptor
     * @return
     */
    @Bean
    public DataPermissionInterceptor dataPermissionInterceptor(MybatisPlusInterceptor interceptor) {
        DataPermissionInterceptor inner = new DataPermissionInterceptor(new OrgDataPermissionHandler(
                (userId) -> SpringUtils.getBean(UserQueryService.class).getDataScopeById(userId)));
        // 添加到 interceptor 中
        // 需要加在首个，主要是为了在分页插件前面。这个是 MyBatis Plus 的规定
        MyBatisUtils.addInterceptor(interceptor, inner, 0);
        return inner;
    }
}
