package com.zsh.cloud.common.log.aop;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.zsh.cloud.common.core.domain.Result;
import com.zsh.cloud.common.core.util.JsonUtils;
import com.zsh.cloud.common.core.util.RequestUtils;
import com.zsh.cloud.common.log.enums.LogTypeEnum;
import com.zsh.cloud.common.log.event.SysLogEvent;
import com.zsh.cloud.common.log.util.LogUtil;
import com.zsh.cloud.common.core.contex.TenantContext;
import com.zsh.cloud.system.api.dto.OptLogDTO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * 系统日志，切面处理类(spring 事件处理).
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/9 14:17
 */
@Slf4j
@Aspect
public class SysLogAspect {
    
    /**
     * 事件发布是由ApplicationContext对象管控的.
     * </p>
     * 我们发布事件前需要注入ApplicationContext对象调用publishEvent方法完成事件发布
     **/
    @Autowired
    private ApplicationContext applicationContext;
    
    private static final ThreadLocal<OptLogDTO> THREAD_LOCAL = new ThreadLocal<>();
    
    /**
     * 切入点.
     */
    @Pointcut("@annotation(com.zsh.cloud.common.log.annotations.SysLog)")
    public void logPointCut() {
    }
    
    /**
     * 之前.
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Before(value = "logPointCut()")
    public void recordLog(JoinPoint joinPoint) throws Throwable {
        tryCatch((val) -> this.saveSysLog(joinPoint));
    }
    
    /**
     * 返回通知
     *
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(returning = "ret", pointcut = "logPointCut()")
    public void doAfterReturning(Object ret) {
        tryCatch((val) -> publishEventPrepare(ret));
        
    }
    
    /**
     * 保存日志.
     *
     * @param joinPoint
     */
    private void saveSysLog(JoinPoint joinPoint) {
        // 开始时间
        OptLogDTO sysLog = get();
        String userName = "空";
        try {
            userName = RequestUtils.getUserName();
        } catch (Exception e) {
            log.error("获取当前登录者失败", e);
        }
        sysLog.setUserName(userName);
        String controllerDescription = "";
        Api api = joinPoint.getTarget().getClass().getAnnotation(Api.class);
        if (api != null) {
            String[] tags = api.tags();
            if (tags != null && tags.length > 0) {
                controllerDescription = tags[0];
            }
        }
        
        String controllerMethodDescription = LogUtil.getControllerMethodDescription(joinPoint);
        if (StringUtils.isBlank(controllerDescription)) {
            sysLog.setDescription(controllerMethodDescription);
        } else {
            sysLog.setDescription(controllerDescription + "-" + controllerMethodDescription);
        }
        
        // 类名
        sysLog.setClassPath(joinPoint.getTarget().getClass().getName());
        //获取执行的方法名
        sysLog.setActionMethod(joinPoint.getSignature().getName());
        
        // 参数
        Object[] args = joinPoint.getArgs();
        
        String strArgs = "";
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(
                RequestContextHolder.getRequestAttributes())).getRequest();
        try {
            if (!request.getContentType().contains("multipart/form-data")) {
                strArgs = JsonUtils.toJsonString(args);
            }
        } catch (Exception e) {
            try {
                strArgs = Arrays.toString(args);
            } catch (Exception ex) {
                log.warn("解析参数异常", ex);
            }
        }
        sysLog.setParams(getText(strArgs));
        
        sysLog.setRequestIp(ServletUtil.getClientIP(request));
        sysLog.setRequestUri(URLUtil.getPath(request.getRequestURI()));
        sysLog.setHttpMethod(request.getMethod());
        sysLog.setUa(StrUtil.sub(request.getHeader("user-agent"), 0, 500));
        sysLog.setStartTime(LocalDateTime.now());
        sysLog.setTenantId(TenantContext.getTenantId());
        THREAD_LOCAL.set(sysLog);
    }
    
    /**
     * 获取日志.
     *
     * @return
     */
    private OptLogDTO get() {
        OptLogDTO sysLog = THREAD_LOCAL.get();
        if (sysLog == null) {
            return new OptLogDTO();
        }
        return sysLog;
    }
    
    /**
     * 函数式方法.
     *
     * @param consumer
     */
    private void tryCatch(Consumer<String> consumer) {
        try {
            consumer.accept("");
        } catch (Exception e) {
            log.warn("记录操作日志异常", e);
            THREAD_LOCAL.remove();
        }
    }
    
    /**
     * 发布之前，准备数据.
     *
     * @param ret
     */
    private void publishEventPrepare(Object ret) {
        Result r;
        if (ret instanceof Result) {
            r = Convert.convert(Result.class, ret);
        } else {
            r = Result.success(ret);
        }
        OptLogDTO sysLog = get();
        if (r == null) {
            sysLog.setType(LogTypeEnum.OPT.getCode());
        } else {
            if (r.getIsSuccess()) {
                sysLog.setType(LogTypeEnum.OPT.getCode());
            } else {
                sysLog.setType(LogTypeEnum.EX.getCode());
                sysLog.setExDetail(r.getMsg());
            }
            sysLog.setResult(getText(r.toString()));
        }
        // 发布
        publishEvent(sysLog);
    }
    
    /**
     * 日志推送事件.
     *
     * @param sysLog
     */
    private void publishEvent(OptLogDTO sysLog) {
        sysLog.setFinishTime(LocalDateTime.now());
        sysLog.setConsumingTime(sysLog.getStartTime().until(sysLog.getFinishTime(), ChronoUnit.MILLIS));
        applicationContext.publishEvent(new SysLogEvent(sysLog));
        THREAD_LOCAL.remove();
    }
    
    /**
     * 截取指定长度的字符串
     *
     * @param val
     * @return
     */
    private String getText(String val) {
        return StrUtil.sub(val, 0, 65535);
    }
}
