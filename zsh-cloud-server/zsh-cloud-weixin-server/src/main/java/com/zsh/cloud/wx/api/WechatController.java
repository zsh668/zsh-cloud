package com.zsh.cloud.wx.api;

import com.zsh.cloud.wx.application.WechatService;
import com.zsh.cloud.wx.application.query.WechatCheckSignatureQuery;
import com.zsh.cloud.wx.application.query.WechatMessageQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信
 *
 * @author hang
 * @version 1.0
 * @date 2022/03/14 10:19
 */
@RestController
@RequestMapping("portal")
@Slf4j
public class WechatController {
    
    @Autowired
    private WechatService wechatService;
    
    /**
     * 认证
     *
     * @param signatureQuery
     * @return java.lang.String
     * @author hang
     * @date 2022/03/15 10:20
     */
    @GetMapping(value = "msg/{appid}", produces = "text/plain;charset=utf-8")
    public String checkToken(@PathVariable("appid") String appid, @Validated WechatCheckSignatureQuery signatureQuery) {
        log.info("接收到来自微信服务器的认证消息：[{}]", signatureQuery.toString());
        // 校验token
        if (wechatService.validateSignature(appid, signatureQuery)) {
            return signatureQuery.getEchostr();
        }
        return "非法请求";
    }
    
    /**
     * 接收微信各类消息
     *
     * @param appid
     * @param messageQuery
     * @param requestBody
     * @return java.lang.String
     * @author hang
     * @date 2022/03/16 14:59
     */
    @PostMapping(value = "msg/{appid}", produces = "text/plain;charset=utf-8")
    public String receiveMessage(@PathVariable("appid") String appid, @Validated WechatMessageQuery messageQuery,
            @RequestBody String requestBody) {
        log.info("接收到认证消息:\n{}", messageQuery.toString());
        log.info("收到消息:\n{}", requestBody);
        // 校验签名
        if (!wechatService.validateSignature(appid, messageQuery)) {
            throw new IllegalArgumentException("非法请求");
        }
        return wechatService.replyMessage(requestBody, appid, messageQuery);
    }
}
