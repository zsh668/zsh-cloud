package com.zsh.cloud.system.application;

import java.util.Set;

/**
 * 资源查询服务接口.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/29 11:03
 */
public interface ResourceQueryService {
    
    /**
     * 获取权限编码
     *
     * @param userId
     * @return
     */
    Set<String> getPermissionCodes(String userId);
}
