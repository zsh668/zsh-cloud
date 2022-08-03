package com.zsh.cloud.sms.application.impl;

import com.zsh.cloud.sms.application.SignatureApplicationService;
import com.zsh.cloud.sms.application.model.command.SignatureCreateCommand;
import com.zsh.cloud.sms.application.model.command.SignatureUpdateCommand;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 签名应用服务实现类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/8/1 16:46
 */
@Service
public class SignatureApplicationServiceImpl implements SignatureApplicationService {
    
    @Override
    public void save(SignatureCreateCommand signatureCommand) {
    
    }
    
    @Override
    public void update(SignatureUpdateCommand signatureCommand) {
    
    }
    
    @Override
    public void deleteBatch(List<String> ids) {
    
    }
}
