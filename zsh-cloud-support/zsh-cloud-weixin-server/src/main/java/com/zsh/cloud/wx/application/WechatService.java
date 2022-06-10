package com.zsh.cloud.wx.application;

import com.zsh.cloud.wx.application.query.WechatMessageQuery;
import com.zsh.cloud.wx.application.query.WechatSignatureQuery;

/**
 * @author hang
 * @version 1.0
 * @date 2022/03/14 10:54
 */
public interface WechatService {
    
    /**
     * 验证token
     *
     *
     * @param appid
     * @param signatureQuery
     * @return boolean
     * @author hang
     * @date 2022/03/15 17:22
     */
    boolean validateSignature(String appid, WechatSignatureQuery signatureQuery);
    
    String replyMessage(String requestBody, String appid, WechatMessageQuery messageQuery);
}
