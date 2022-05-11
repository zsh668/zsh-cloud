package com.zsh.cloud.gateway.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * 鉴权管理器.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/22 11:32
 */
@Slf4j
@Component
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {
    
    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
        String restPath = request.getMethodValue() + "_" + request.getURI().getPath();
        log.info("请求路径：{}", restPath);
        // 对应跨域的预检请求直接放行
        if (request.getMethod() == HttpMethod.OPTIONS) {
            return Mono.just(new AuthorizationDecision(true));
        }
        return mono.map(auth -> new AuthorizationDecision(auth.isAuthenticated()))
                .defaultIfEmpty(new AuthorizationDecision(false));
    }
}
