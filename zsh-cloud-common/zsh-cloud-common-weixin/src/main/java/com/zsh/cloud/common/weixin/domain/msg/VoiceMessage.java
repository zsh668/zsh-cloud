package com.zsh.cloud.common.weixin.domain.msg;

import com.zsh.cloud.common.weixin.domain.base.BaseMessage;
import lombok.Data;

/**
 * 语音消息
 *
 * @author hang
 * @version 1.0
 * @date 2022/03/16 15:28
 */
@Data
public class VoiceMessage extends BaseMessage {
    
    /**
     * 语音消息媒体id，可以调用获取临时素材接口拉取数据。
     */
    private String mediaId;
    
    /**
     * 语音格式，如amr，speex等
     */
    private String format;
    
    /**
     * 语音识别结果，UTF8编码
     */
    private String recognition;
}
