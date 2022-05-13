package com.zsh.cloud.common.web.translate;

import org.springframework.context.SmartLifecycle;

import javax.annotation.Resource;

/**
 * 抽象转换注册 策略管理.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/13 17:21
 */
public abstract class AbstractTranslateConvertRegistry implements SmartLifecycle {
    
    @Resource
    private TranslateConvertFactory translateConvertFactory;
    
    public abstract TranslateTypeConvert addTranslateTypeConvert();
    
    @Override
    public void start() {
        translateConvertFactory.addConvert(addTranslateTypeConvert());
    }
    
    @Override
    public void stop() {
    
    }
    
    @Override
    public boolean isRunning() {
        return false;
    }
}
