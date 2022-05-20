package com.zsh.cloud.auth.controller;

import cn.hutool.core.io.IoUtil;
import com.zsh.cloud.auth.sevice.CaptchaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 验证码.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/19 14:38
 */
@Slf4j
@Api(tags = "验证码管理")
@RestController
public class CaptchaController {
    
    @Autowired
    private CaptchaService captchaApplicationService;
    
    /**
     * 获取验证码.
     *
     * @param key
     * @param response
     * @throws IOException
     */
    @ApiOperation(notes = "验证码", value = "验证码")
    @ApiImplicitParams({@ApiImplicitParam(name = "key", value = "唯一key", required = true)})
    @GetMapping("/captcha")
    public void captcha(@RequestParam(value = "key") String key, HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        //获取图片验证码
        BufferedImage image = captchaApplicationService.getCaptcha(key);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IoUtil.close(out);
    }
}
