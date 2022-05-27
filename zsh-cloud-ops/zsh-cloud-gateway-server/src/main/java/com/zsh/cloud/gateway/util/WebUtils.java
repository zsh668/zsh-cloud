package com.zsh.cloud.gateway.util;

import com.zsh.cloud.common.core.domain.IDict;
import com.zsh.cloud.common.core.domain.Result;
import com.zsh.cloud.common.core.exception.code.enums.GlobalErrorCode;
import com.zsh.cloud.common.core.util.JsonUtils;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * web工具类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/22 11:15
 */
public class WebUtils {
    
    private static final String ALLOWED_HEADERS = "X-Requested-With, tenant_id, Blade-Auth, Content-Type, Authorization, credential, X-XSRF-TOKEN, token, username, client, knfie4j-gateway-request, request-origion";
    
    private static final String ALLOWED_METHODS = "GET,POST,PUT,DELETE,OPTIONS,HEAD";
    
    private static final String ALLOWED_ORIGIN = "http://127.0.0.1:8001";
    
    private static final String ALLOWED_EXPOSE = "*";
    
    private static final String MAX_AGE = "18000L";
    
    public static Mono<Void> getAuthFailResult(ServerHttpResponse response, Integer code) {
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        response.getHeaders().add("Access-Control-Allow-Headers", ALLOWED_HEADERS);
        response.getHeaders().add("Access-Control-Allow-Methods", ALLOWED_METHODS);
        response.getHeaders().add("Access-Control-Allow-Origin", ALLOWED_ORIGIN);
        response.getHeaders().add("Access-Control-Expose-Headers", ALLOWED_EXPOSE);
        response.getHeaders().add("Access-Control-Max-Age", MAX_AGE);
        response.getHeaders().add("Access-Control-Allow-Credentials", "true");
        byte[] responseByte = JsonUtils.toJsonString(
                Result.error(code, IDict.getTextByCode(GlobalErrorCode.class, code))).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(responseByte);
        return response.writeWith(Flux.just(buffer));
    }
}
