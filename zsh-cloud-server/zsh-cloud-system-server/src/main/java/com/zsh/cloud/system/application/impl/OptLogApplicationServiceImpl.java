package com.zsh.cloud.system.application.impl;

import com.zsh.cloud.common.log.dto.OptLogDTO;
import com.zsh.cloud.system.application.OptLogApplicationService;
import org.springframework.stereotype.Service;

/**
 * 系统日志应用服务实现类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/9 18:58
 */
@Service
public class OptLogApplicationServiceImpl implements OptLogApplicationService {
    
    @Override
    public void save(OptLogDTO optLog) {
        // 保存
    }
}
