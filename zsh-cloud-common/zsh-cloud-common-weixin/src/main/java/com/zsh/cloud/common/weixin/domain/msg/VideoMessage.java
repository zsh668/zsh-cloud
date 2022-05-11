package com.zsh.cloud.common.weixin.domain.msg;

import com.zsh.cloud.common.weixin.domain.base.BaseMessage;
import lombok.Data;

/**
 * 视频消息
 *
 * @author hang
 * @version 1.0
 * @date 2022/03/16 15:28
 */
@Data
public class VideoMessage extends BaseMessage {
    
    /**
     * 语音消息媒体id，可以调用获取临时素材接口拉取数据。
     */
    private String mediaId;
    
    /**
     * 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    private String thumbMediaId;
}
