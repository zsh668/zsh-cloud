package com.zsh.cloud.common.weixin.handler;

import java.util.Map;

/**
 * 消息处理
 *
 * @author hang
 * @version 1.0
 * @date 2022/03/17 16:07
 */
public interface MessageHandler<T> {
    
    /**
     * 消息转换对象
     */
    T converter(Map<String, String> map);
}
