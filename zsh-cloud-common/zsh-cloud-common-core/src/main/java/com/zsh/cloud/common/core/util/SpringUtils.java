package com.zsh.cloud.common.core.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 获取bean工具类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/12 19:13
 */
@Component
public class SpringUtils implements ApplicationContextAware {
    
    private static ApplicationContext applicationContext;
    
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
    
    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }
    
    public static <T> T getBean(String name, Class<T> type) {
        return applicationContext.getBean(name, type);
    }
    
    public static <T> T getBean(Class<T> type) {
        return applicationContext.getBean(type);
    }
    
    public static <T> Map<String, T> getBeansOfType(Class<T> type) {
        return applicationContext.getBeansOfType(type);
    }
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtils.applicationContext = applicationContext;
    }
}
