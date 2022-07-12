package com.zsh.cloud.common.web.handler;

import com.zsh.cloud.common.core.domain.Result;
import com.zsh.cloud.common.core.util.JsonUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 处理统一返回信息.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/11 10:57
 */
@RestControllerAdvice(basePackages = {"com.zsh.cloud"})
public class ControllerResponseAdvice implements ResponseBodyAdvice<Object> {
    
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // response是Result类型，或者注释了NotControllerResponseAdvice都不进行包装
        return !(returnType.getParameterType().isAssignableFrom(Result.class) || returnType.hasMethodAnnotation(
                NotControllerResponseAdvice.class));
    }
    
    @Override
    public Object beforeBodyWrite(Object returnValue, MethodParameter returnType, MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
            ServerHttpResponse response) {
        // String类型不能直接包装
        if (returnType.getGenericParameterType().equals(String.class)) {
            // 将数据包装在ResultVo里后转换为json串进行返回
            return JsonUtils.toJsonString(Result.success(returnValue));
        }
        // 否则直接包装成ResultVo返回
        return Result.success(returnValue);
    }
}
