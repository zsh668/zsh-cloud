package com.zsh.cloud.system.application;

import java.util.List;

/**
 * 登录日志应用服务接口.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/7 10:12
 */
public interface LoginLogApplicationService {
    
    /**
     * 批量删除
     *
     * @param ids
     */
    void deleteBatch(List<String> ids);
}
