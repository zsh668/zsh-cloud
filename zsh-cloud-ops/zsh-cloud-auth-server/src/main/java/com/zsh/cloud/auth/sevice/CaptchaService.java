package com.zsh.cloud.auth.sevice;

import com.wf.captcha.base.Captcha;

import java.awt.image.BufferedImage;

/**
 * 验证码服务.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/19 18:20
 */
public interface CaptchaService {
    
    /**
     * 生成验证码.
     *
     * @param uuid
     * @return
     */
    Captcha getCaptcha(String uuid);
    
    /**
     * 校验 验证码
     *
     * @param uuid
     * @param captchaCode
     * @return
     */
    boolean validateCaptcha(String uuid, String captchaCode);
}
