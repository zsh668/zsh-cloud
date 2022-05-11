package com.zsh.cloud.gateway.filter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.nimbusds.jose.JWSObject;
import com.zsh.cloud.common.core.constant.AuthConstants;
import com.zsh.cloud.common.core.constant.CacheKey;
import com.zsh.cloud.common.core.exception.code.enums.GlobalErrorCode;
import com.zsh.cloud.gateway.util.WebUtils;
import lombok.SneakyThrows;
import net.oschina.j2cache.CacheChannel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 全局过滤器.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/22 11:35
 */
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {
    
    @Autowired
    private CacheChannel cache;
    
    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        
        // 非JWT或者JWT为空不作处理
        String token = request.getHeaders().getFirst(AuthConstants.AUTHORIZATION_KEY);
        if (StrUtil.isBlank(token) || !token.startsWith(AuthConstants.AUTHORIZATION_PREFIX)) {
            return chain.filter(exchange);
        }
        
        // 解析JWT获取jti，以jti为key判断redis的黑名单列表是否存在，存在拦截响应token失效
        token = token.replace(AuthConstants.AUTHORIZATION_PREFIX, StringUtils.EMPTY);
        JWSObject jwsObject = JWSObject.parse(token);
        String payload = jwsObject.getPayload().toString();
        JSONObject jsonObject = JSONUtil.parseObj(payload);
        String jti = jsonObject.getStr(AuthConstants.JWT_JTI);
        // 是否在黑名单
        boolean isBlack = cache.exists(CacheKey.TOKEN_BLACKLIST_PREFIX, jti);
        if (isBlack) {
            return WebUtils.getAuthFailResult(response, GlobalErrorCode.UNAUTHORIZED.getCode());
        }
        
        // 存在token且不是黑名单，request写入JWT的载体信息
        request = exchange.getRequest().mutate().header(AuthConstants.JWT_PAYLOAD_KEY, payload).build();
        exchange = exchange.mutate().request(request).build();
        return chain.filter(exchange);
    }
    
    @Override
    public int getOrder() {
        return 0;
    }
}
