package com.zsh.cloud.common.web.repeat;

import cn.hutool.crypto.digest.DigestUtil;
import com.zsh.cloud.common.core.constant.CacheKey;
import com.zsh.cloud.common.core.exception.ServiceException;
import com.zsh.cloud.common.core.exception.code.enums.GlobalErrorCode;
import com.zsh.cloud.common.core.util.JsonUtils;
import com.zsh.cloud.common.core.util.RequestUtils;
import com.zsh.cloud.common.redis.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * 重复操作AOP.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/13 17:21
 */
@Aspect
@Component
@Slf4j
public class RepeatRequestAspect {
    
    @Resource
    private RedisService redisService;
    
    /**
     * 环绕.
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("@annotation(com.zsh.cloud.common.web.repeat.RepeatRequestIntercept)")
    public Object before(ProceedingJoinPoint joinPoint) throws Throwable {
        String userId = RequestUtils.getUserId();
        Object[] args = joinPoint.getArgs();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        
        String requestInfo = joinPoint.getTarget() + method.getName() + userId + userId + JsonUtils.toJsonString(args);
        
        String md5 = DigestUtil.md5Hex(requestInfo.getBytes());
        String lockKey = CacheKey.REPEAT_REQUEST_REDIS_KEY.concat(md5);
        Boolean lockFlag = false;
        try {
            lockFlag = redisService.setNx(lockKey, md5);
            if (lockFlag) {
                return joinPoint.proceed();
            } else {
                throw new ServiceException(GlobalErrorCode.REPETITIVE_OPERATION);
            }
        } finally {
            if (lockFlag) {
                redisService.delete(lockKey);
            }
        }
    }
}
