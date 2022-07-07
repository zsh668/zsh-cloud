package com.zsh.cloud.system.application;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.system.application.model.dto.OptLogInfoDTO;
import com.zsh.cloud.system.application.model.dto.OptLogPageDTO;
import com.zsh.cloud.system.application.model.query.OptLogPageQuery;

/**
 * 操作日志查询服务接口.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/7 10:12
 */
public interface OptLogQueryService {
    
    /**
     * 分页查询.
     *
     * @param optLogPageQuery
     * @return
     */
    Page<OptLogPageDTO> queryPage(OptLogPageQuery optLogPageQuery);
    
    /**
     * 根据ID查询.
     *
     * @param id
     * @return
     */
    OptLogInfoDTO find(String id);
}
