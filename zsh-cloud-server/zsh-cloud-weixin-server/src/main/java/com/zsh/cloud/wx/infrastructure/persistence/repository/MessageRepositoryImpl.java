package com.zsh.cloud.wx.infrastructure.persistence.repository;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.cloud.common.weixin.domain.msg.TextMessage;
import com.zsh.cloud.common.weixin.util.xml.XmlUtils;
import com.zsh.cloud.wx.domain.model.message.MessageRepository;
import com.zsh.cloud.wx.infrastructure.persistence.entity.WxMessageDO;
import com.zsh.cloud.wx.infrastructure.persistence.mapper.WxMessageMapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author hang
 * @version 1.0
 * @date 2022/03/17 16:21
 */
@Repository
public class MessageRepositoryImpl extends ServiceImpl<WxMessageMapper, WxMessageDO>
        implements MessageRepository, IService<WxMessageDO> {
    
    @Override
    public String findReplyMessage(String appid, Map<String, String> map) {
        TextMessage textMessage = BeanUtil.toBeanIgnoreCase(map, TextMessage.class, true);
        final String fromUserName = textMessage.getFromUserName();
        final String toUserName = textMessage.getToUserName();
        textMessage.setToUserName(fromUserName);
        textMessage.setFromUserName(toUserName);
        final String message = XmlUtils.textMessageToXml(textMessage);
        return message;
    }
}
