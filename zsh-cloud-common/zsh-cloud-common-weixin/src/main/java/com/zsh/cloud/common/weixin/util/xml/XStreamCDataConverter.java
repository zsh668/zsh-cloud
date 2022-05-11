package com.zsh.cloud.common.weixin.util.xml;

/**
 * CDATA 内容转换器，加上CDATA标签.
 *
 * @author hang
 * @version 1.0
 * @date 2022/03/16 15:24
 */
public class XStreamCDataConverter {
    
    public String toString(Object obj) {
        return "<![CDATA[" + obj + "]]>";
    }
}
