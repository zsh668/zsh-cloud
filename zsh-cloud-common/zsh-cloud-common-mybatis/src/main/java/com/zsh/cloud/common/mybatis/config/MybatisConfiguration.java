package com.zsh.cloud.common.mybatis.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.zsh.cloud.common.mybatis.core.handler.DefaultFieldHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Mybatis 配置.
 *
 * @author hang
 * @version 1.0
 * @date 2022/03/11 18:04
 */
@Configuration
public class MybatisConfiguration {
    
    /**
     * 分页插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
    
    /**
     * 自动填充参数类
     *
     * @return
     */
    @Bean
    public MetaObjectHandler defaultMetaObjectHandler() {
        return new DefaultFieldHandler();
    }
}
