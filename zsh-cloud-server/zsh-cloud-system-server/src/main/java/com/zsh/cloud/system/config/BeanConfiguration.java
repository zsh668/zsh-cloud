package com.zsh.cloud.system.config;

import com.zsh.cloud.common.core.id.CodeGenerate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Bean配置.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/6 16:36
 */
@Configuration
public class BeanConfiguration {
    
    /**
     * 长度都是8位的字符串.
     *
     * @param machineCode 机器码.
     * @return
     */
    @Bean("codeGenerate")
    public CodeGenerate codeGenerate(@Value("${zsh.cloud.id-generator.machine-code:1}") Long machineCode) {
        return new CodeGenerate(machineCode.intValue());
    }
}
