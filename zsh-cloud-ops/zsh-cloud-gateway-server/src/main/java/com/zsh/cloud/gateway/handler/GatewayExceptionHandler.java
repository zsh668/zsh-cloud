package com.zsh.cloud.gateway.handler;

import com.zsh.cloud.common.core.domain.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.Assert;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.ConnectException;
import java.util.Collections;
import java.util.List;

/**
 * 网关统一异常处理器.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/22 17:10
 */
@Slf4j
public class GatewayExceptionHandler implements ErrorWebExceptionHandler {
    
    private List<HttpMessageReader<?>> messageReaders = Collections.emptyList();
    
    private List<HttpMessageWriter<?>> messageWriters = Collections.emptyList();
    
    private List<ViewResolver> viewResolvers = Collections.emptyList();
    
    /**
     * 存储处理异常后的信息
     */
    private ThreadLocal<Result> exceptionHandlerResult = new ThreadLocal<>();
    
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        Result result = Result.error(formatMessage(ex));
        
        ServerHttpRequest request = exchange.getRequest();
        
        log.error("[全局异常处理]异常请求路径:{},记录异常信息:{}", request.getPath(), ex.getMessage());
        
        /**
         * 参考 {@link org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler}
         */
        if (exchange.getResponse().isCommitted()) {
            return Mono.error(ex);
        }
        exceptionHandlerResult.set(result);
        ServerRequest newRequest = ServerRequest.create(exchange, this.messageReaders);
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse).route(newRequest)
                .switchIfEmpty(Mono.error(ex)).flatMap(handler -> handler.handle(newRequest))
                .flatMap(response -> write(exchange, response));
    }
    
    /**
     * 参考 {@link org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler}
     *
     * @param request
     * @return
     */
    protected Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
        Result result = exceptionHandlerResult.get();
        return ServerResponse.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(result));
    }
    
    /**
     * 参考 {@link org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler}
     *
     * @param exchange
     * @param response
     * @return
     */
    private Mono<? extends Void> write(ServerWebExchange exchange, ServerResponse response) {
        exchange.getResponse().getHeaders().setContentType(response.headers().getContentType());
        return response.writeTo(exchange, new ResponseContext());
    }
    
    /**
     * 参考 {@link org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler}
     */
    private class ResponseContext implements ServerResponse.Context {
        
        @Override
        public List<HttpMessageWriter<?>> messageWriters() {
            return GatewayExceptionHandler.this.messageWriters;
        }
        
        @Override
        public List<ViewResolver> viewResolvers() {
            return GatewayExceptionHandler.this.viewResolvers;
        }
        
    }
    
    /**
     * 参考 {@link org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler}
     *
     * @param messageReaders
     */
    public void setMessageReaders(List<HttpMessageReader<?>> messageReaders) {
        Assert.notNull(messageReaders, "'messageReaders' must not be null");
        this.messageReaders = messageReaders;
    }
    
    /**
     * 参考 {@link org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler}
     *
     * @param messageWriters
     */
    public void setMessageWriters(List<HttpMessageWriter<?>> messageWriters) {
        Assert.notNull(messageWriters, "'messageWriters' must not be null");
        this.messageWriters = messageWriters;
    }
    
    /**
     * 参考 {@link org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler}
     *
     * @param viewResolvers
     */
    public void setViewResolvers(List<ViewResolver> viewResolvers) {
        this.viewResolvers = viewResolvers;
    }
    
    protected String formatMessage(Throwable ex) {
        String errorMessage = null;
        if (ex instanceof NotFoundException) {
            // 接口路径404
            errorMessage = "请求服务迷路了";
        } else if (ex instanceof ConnectException) {
            errorMessage = "系统正在维护，请稍后再访问";
        } else if (ex instanceof ResponseStatusException) {
            ResponseStatusException responseStatusException = (ResponseStatusException) ex;
            errorMessage = responseStatusException.getMessage();
        } else {
            errorMessage = "服务器异常，请稍后重试";
        }
        return errorMessage;
    }
}
