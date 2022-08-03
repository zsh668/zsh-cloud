package com.zsh.cloud.sms.application;

import com.zsh.cloud.sms.application.model.command.SignatureCreateCommand;
import com.zsh.cloud.sms.application.model.command.SignatureUpdateCommand;

import java.util.List;

/**
 * 签名应用服务接口.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/8/1 16:38
 */
public interface SignatureApplicationService {
    
    /**
     * 保存.
     *
     * @param signatureCommand
     */
    void save(SignatureCreateCommand signatureCommand);
    
    /**
     * 更新.
     *
     * @param signatureCommand
     */
    void update(SignatureUpdateCommand signatureCommand);
    
    /**
     * 批量删除.
     *
     * @param ids
     */
    void deleteBatch(List<String> ids);
}
