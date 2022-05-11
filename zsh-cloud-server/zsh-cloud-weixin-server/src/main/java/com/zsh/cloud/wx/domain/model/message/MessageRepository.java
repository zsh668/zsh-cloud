package com.zsh.cloud.wx.domain.model.message;

import java.util.Map;

/**
 * @author hang
 * @version 1.0
 * @date 2022/03/17 16:18
 */
public interface MessageRepository {
    
    String findReplyMessage(String appid, Map<String, String> map);
    
}
