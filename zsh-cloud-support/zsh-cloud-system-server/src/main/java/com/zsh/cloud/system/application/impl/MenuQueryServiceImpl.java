package com.zsh.cloud.system.application.impl;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.system.application.MenuQueryService;
import com.zsh.cloud.system.application.dto.MenuDTO;
import com.zsh.cloud.system.application.dto.MenuTreeDTO;
import com.zsh.cloud.system.application.query.MenuPageQuery;
import org.springframework.stereotype.Service;

/**
 * 菜单查询服务实现类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/17 16:35
 */
@Service
public class MenuQueryServiceImpl implements MenuQueryService {
    
    @Override
    public Page<MenuTreeDTO> queryList(MenuPageQuery menuPageQuery) {
        return null;
    }
    
    @Override
    public MenuDTO find(String id) {
        return null;
    }
}
