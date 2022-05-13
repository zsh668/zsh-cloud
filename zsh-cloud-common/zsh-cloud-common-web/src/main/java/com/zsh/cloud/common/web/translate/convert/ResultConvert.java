package com.zsh.cloud.common.web.translate.convert;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.common.core.domain.Result;
import com.zsh.cloud.common.web.translate.AbstractTranslateConvertRegistry;
import com.zsh.cloud.common.web.translate.TranslateTypeConvert;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 统一返回转换.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/13 17:21
 */
@Configuration
public class ResultConvert {
    
    public static class ResultWrapperTranslateTypeConvert implements TranslateTypeConvert<Result> {
        
        @Override
        public Object convert(Result object) {
            Object data = object.getData();
            //如果是page则拿到data 再返回
            if (data instanceof Page) {
                return ((Page<?>) data).getList();
            }
            return data;
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
