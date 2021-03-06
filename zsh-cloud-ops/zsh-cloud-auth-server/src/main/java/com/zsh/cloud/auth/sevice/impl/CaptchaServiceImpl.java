package com.zsh.cloud.auth.sevice.impl;

import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.base.Captcha;
import com.zsh.cloud.auth.sevice.CaptchaService;
import com.zsh.cloud.common.core.constant.CacheKey;
import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.CacheObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 验证码服务实现类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/19 18:22
 */
@Service
public class CaptchaServiceImpl implements CaptchaService {
    
    @Autowired
    private CacheChannel cache;
    
    @Override
    public Captcha getCaptcha(String uuid) {
        //生成验证码
        Captcha captcha = new ArithmeticCaptcha(115, 42);
        captcha.setCharType(2);
        // 保存
        cache.set(CacheKey.CAPTCHA, uuid, StringUtils.lowerCase(captcha.text()));
        return captcha;
    }
    
    @Override
    public boolean validateCaptcha(String uuid, String captchaCode) {
        if (StringUtils.isBlank(uuid) || StringUtils.isBlank(captchaCode)) {
            return false;
        }
        // 获取验证码
        CacheObject cacheObject = cache.get(CacheKey.CAPTCHA, uuid);
        String code = String.valueOf(cacheObject.getValue());
        // 移除 验证码
        cache.evict(CacheKey.CAPTCHA, uuid);
        return captchaCode.equalsIgnoreCase(code);
    }
}
