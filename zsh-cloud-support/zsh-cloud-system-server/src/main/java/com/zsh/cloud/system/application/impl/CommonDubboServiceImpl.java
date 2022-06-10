package com.zsh.cloud.system.application.impl;

import com.zsh.cloud.common.core.id.CodeGenerate;
import com.zsh.cloud.system.api.dubbo.CommonDubboService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 公共查询服务实现类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/6 16:45
 */
@DubboService
public class CommonDubboServiceImpl implements CommonDubboService {
    
    @Autowired
    private CodeGenerate codeGenerate;
    
    @Override
    public String getCode() {
        return codeGenerate.next();
    }
}
