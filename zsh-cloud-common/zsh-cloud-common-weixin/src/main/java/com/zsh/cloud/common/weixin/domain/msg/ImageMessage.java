package com.zsh.cloud.common.weixin.domain.msg;

import com.zsh.cloud.common.weixin.domain.base.BaseMessage;
import lombok.Data;

/**
 * 图片消息
 *
 * @author hang
 * @version 1.0
 * @date 2022/03/16 15:28
 */
@Data
public class ImageMessage extends BaseMessage {
    
    /**
     * 图片链接（由系统生成）
     */
    private String picUrl;
    
    /**
     * 图片消息媒体id，可以调用获取临时素材接口拉取数据。
     */
    private String mediaId;
}
