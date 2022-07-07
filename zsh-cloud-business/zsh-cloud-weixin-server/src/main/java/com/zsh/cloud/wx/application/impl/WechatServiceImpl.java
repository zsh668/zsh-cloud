package com.zsh.cloud.wx.application.impl;

import com.zsh.cloud.common.weixin.util.aes.WxBizMsgCrypt;
import com.zsh.cloud.common.weixin.util.xml.XmlUtils;
import com.zsh.cloud.wx.application.WechatService;
import com.zsh.cloud.wx.application.model.query.WechatMessageQuery;
import com.zsh.cloud.wx.application.model.query.WechatSignatureQuery;
import com.zsh.cloud.wx.domain.model.account.Account;
import com.zsh.cloud.wx.domain.model.account.AccountRepository;
import com.zsh.cloud.wx.domain.model.account.AppId;
import com.zsh.cloud.wx.domain.model.message.MessageRepository;
import com.zsh.cloud.wx.domain.model.token.Signature;
import com.zsh.cloud.wx.domain.model.token.TokenSignature;
import com.zsh.cloud.wx.domain.service.WechatSignatureValidateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author hang
 * @version 1.0
 * @date 2022/03/14 10:55
 */
@Slf4j
@Service
public class WechatServiceImpl implements WechatService {
    
    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private MessageRepository messageRepository;
    
    /**
     * 验证token
     *
     * @param appid
     * @param signatureQuery
     * @return boolean
     * @author hang
     * @date 2022/03/15 17:22
     */
    @Override
    public boolean validateSignature(String appid, WechatSignatureQuery signatureQuery) {
        Account account = accountRepository.find(new AppId(appid));
        
        WechatSignatureValidateService wechatSignatureValidateService = new WechatSignatureValidateService();
        return wechatSignatureValidateService.validate(
                new TokenSignature(account.getToken(), signatureQuery.getTimestamp(), signatureQuery.getNonce()),
                new Signature(signatureQuery.getSignature()));
    }
    
    @Override
    public String replyMessage(String requestBody, String appid, WechatMessageQuery messageQuery) {
        Account account = accountRepository.find(new AppId(appid));
    
        WxBizMsgCrypt cmsCrypt = new WxBizMsgCrypt(account.getToken().getToken(), account.getAesKey(), appid);
        if ("aes".equalsIgnoreCase(messageQuery.getEncrypt_type())) {
            requestBody = cmsCrypt.decrypt(messageQuery.getMsg_signature(), messageQuery.getTimestamp(),
                    messageQuery.getNonce(), requestBody);
        }
        Map<String, String> map = XmlUtils.parseXml(requestBody);
        log.info("解析后信息: {}", map);
        String msg = messageRepository.findReplyMessage(appid, map);
        if ("aes".equalsIgnoreCase(messageQuery.getEncrypt_type())) {
            msg = cmsCrypt.encrypt(msg);
        }
        return msg;
    }
}
