package com.zsh.cloud.common.weixin.domain.base;

import lombok.Data;

/**
 * 公共的消息字段
 *
 * @author hang
 * @version 1.0
 * @date 2022/03/16 15:19
 */
@Data
public class BaseEvent extends Base {
    
    /**
     * 事件类型
     */
    private String event;
}
