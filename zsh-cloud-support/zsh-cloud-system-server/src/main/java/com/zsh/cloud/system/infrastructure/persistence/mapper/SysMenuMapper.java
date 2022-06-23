package com.zsh.cloud.system.infrastructure.persistence.mapper;

import com.zsh.cloud.common.mybatis.core.mapper.BaseMapperExt;
import com.zsh.cloud.common.mybatis.util.Wraps;
import com.zsh.cloud.system.application.query.MenuPageQuery;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysMenuDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 菜单Mapper.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/20 14:39
 */
@Mapper
public interface SysMenuMapper extends BaseMapperExt<SysMenuDO> {
    
    /**
     * 根据条件查询.
     *
     * @param menuPageQuery
     * @return
     */
    default List<SysMenuDO> selectList(MenuPageQuery menuPageQuery) {
        return selectList(Wraps.<SysMenuDO>lbQ().likeIfPresent(SysMenuDO::getMenuName, menuPageQuery.getMenuName()));
    }
}
