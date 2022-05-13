package com.zsh.cloud.common.web.translate.convert;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.common.web.translate.AbstractTranslateConvertRegistry;
import com.zsh.cloud.common.web.translate.TranslateTypeConvert;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 分页转换.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/13 17:21
 */
@Configuration
public class PageConvert {
    
    public static class ResultWrapperTranslateTypeConvert implements TranslateTypeConvert<Page> {
        
        @Override
        public Object convert(Page object) {
            return object.getList();
        }
    }
    
    @Bean
    public AbstractTranslateConvertRegistry initTranslateConvertRegistry() {
        return new AbstractTranslateConvertRegistry() {
            
            @Override
            public TranslateTypeConvert addTranslateTypeConvert() {
                return new ResultWrapperTranslateTypeConvert();
            }
        };
    }
}
