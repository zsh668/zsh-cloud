package com.zsh.cloud.common.exception.dubbo;

import com.alibaba.fastjson.JSON;
import com.zsh.cloud.common.core.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.common.utils.ReflectUtils;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.service.GenericService;

import java.lang.reflect.Method;

/**
 * dubbo异常拦截器.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/11 17:27
 */
@Slf4j
@Activate(group = {CommonConstants.PROVIDER, CommonConstants.CONSUMER}, order = -1000) //,
public class DubboExceptionFilter implements Filter, Filter.Listener {
    
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Result invoke;
        try {
            invoke = invoker.invoke(invocation);
            if (invoke != null) {
                if (invoke.getException() != null) {
                    if (invoke.getException().getClass() == ServiceException.class) {
                        return invoke;
                    }
                    Throwable exception = invoke.getException();
                    log.error("dubbo 接口异常", invoke.getException());
                    String errorInfo =
                            "Got unchecked and undeclared exception which called by " + RpcContext.getContext()
                                    .getRemoteHost() + ". service: " + invoker.getInterface().getName() + ", method: "
                                    + invocation.getMethodName() + ", exception: " + exception.getClass().getName()
                                    + ": " + exception.getMessage() + ", args: " + JSON.toJSONString(
                                    invocation.getArguments());
                    throw new RpcException(errorInfo);
                }
            }
        } catch (RpcException | ServiceException e) {
            throw e;
        } catch (Throwable e) {
            log.error(
                    "dubbo rpc error" + RpcContext.getContext().getRemoteHost() + ". service: " + invoker.getInterface()
                            .getName() + ", method: " + invocation.getMethodName() + ", exception: " + e.getClass()
                            .getName() + ": " + e.getMessage(), e);
            throw e;
        }
        return invoke;
    }
    
    @Override
    public void onResponse(Result appResponse, Invoker<?> invoker, Invocation invocation) {
        if (appResponse.hasException() && GenericService.class != invoker.getInterface()) {
            try {
                Throwable exception = appResponse.getException();
                
                if (exception instanceof ServiceException) {
                    return;
                }
                
                // directly throw if it's checked exception
                if (!(exception instanceof RuntimeException) && (exception instanceof Exception)) {
                    return;
                }
                // directly throw if the exception appears in the signature
                try {
                    Method method = invoker.getInterface()
                            .getMethod(invocation.getMethodName(), invocation.getParameterTypes());
                    Class<?>[] exceptionClassses = method.getExceptionTypes();
                    for (Class<?> exceptionClass : exceptionClassses) {
                        if (exception.getClass().equals(exceptionClass)) {
                            return;
                        }
                    }
                } catch (NoSuchMethodException e) {
                    return;
                }
                
                // for the exception not found in method's signature, print ERROR message in server's log.
                log.error("Got unchecked and undeclared exception which called by " + RpcContext.getContext()
                        .getRemoteHost() + ". service: " + invoker.getInterface().getName() + ", method: "
                        + invocation.getMethodName() + ", exception: " + exception.getClass().getName() + ": "
                        + exception.getMessage(), exception);
                
                // directly throw if exception class and interface class are in the same jar file.
                String serviceFile = ReflectUtils.getCodeBase(invoker.getInterface());
                String exceptionFile = ReflectUtils.getCodeBase(exception.getClass());
                if (serviceFile == null || exceptionFile == null || serviceFile.equals(exceptionFile)) {
                    return;
                }
                // directly throw if it's JDK exception
                String className = exception.getClass().getName();
                if (className.startsWith("java.") || className.startsWith("javax.")) {
                    return;
                }
                // directly throw if it's dubbo exception
                if (exception instanceof RpcException) {
                    return;
                }
                
                // otherwise, wrap with RuntimeException and throw back to the client
                appResponse.setException(new RuntimeException(StringUtils.toString(exception)));
            } catch (Throwable e) {
                log.warn("Fail to ExceptionFilter when called by " + RpcContext.getContext().getRemoteHost()
                        + ". service: " + invoker.getInterface().getName() + ", method: " + invocation.getMethodName()
                        + ", exception: " + e.getClass().getName() + ": " + e.getMessage(), e);
            }
        }
    }
    
    @Override
    public void onError(Throwable t, Invoker<?> invoker, Invocation invocation) {
    
    }
}
