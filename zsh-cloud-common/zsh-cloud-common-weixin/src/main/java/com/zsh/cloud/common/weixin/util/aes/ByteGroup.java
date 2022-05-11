package com.zsh.cloud.common.weixin.util.aes;

import java.util.ArrayList;

public class ByteGroup {
    
    ArrayList<Byte> byteContainer = new ArrayList<Byte>();
    
    /**
     * 转换成字节
     *
     * @return byte[]
     * @author hang
     * @date 2022/03/16 17:17
     */
    public byte[] toBytes() {
        byte[] bytes = new byte[byteContainer.size()];
        for (int i = 0; i < byteContainer.size(); i++) {
            bytes[i] = byteContainer.get(i);
        }
        return bytes;
    }
    
    /**
     * 添加字节
     *
     * @param bytes
     * @return com.zsh.cloud.common.core.util.aes.ByteGroup
     * @author hang
     * @date 2022/03/16 17:17
     */
    public ByteGroup addBytes(byte[] bytes) {
        for (byte b : bytes) {
            byteContainer.add(b);
        }
        return this;
    }
    
    public int size() {
        return byteContainer.size();
    }
}
