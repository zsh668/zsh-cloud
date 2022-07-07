package com.zsh.cloud.system.application;

import com.zsh.cloud.system.application.model.dto.MenuDTO;
import com.zsh.cloud.system.application.model.dto.MenuResourceTreeDTO;
import com.zsh.cloud.system.application.model.dto.MenuTreeDTO;
import com.zsh.cloud.system.application.model.dto.VueRouterDTO;
import com.zsh.cloud.system.application.model.query.MenuPageQuery;
import com.zsh.cloud.system.application.model.query.RouterQuery;

import java.util.List;

/**
 * 菜单查询服务接口.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/17 15:01
 */
public interface MenuQueryService {
    
    /**
     * 查询菜单树.
     *
     * @param menuPageQuery
     * @return
     */
    List<MenuTreeDTO> queryList(MenuPageQuery menuPageQuery);
    
    /**
     * 查询.
     *
     * @param id
     * @return
     */
    MenuDTO find(String id);
    
    /**
     * 查询路由树.
     *
     * @param routerQuery
     * @return
     */
    List<VueRouterDTO> queryRouterList(RouterQuery routerQuery);
    
    /**
     * 查询菜单资源树.
     *
     * @return
     */
    List<MenuResourceTreeDTO> queryMenuResourceList();
}
