package com.zsh.cloud.system.infrastructure.persistence.mapper;

import com.zsh.cloud.common.core.enums.BooleanEnum;
import com.zsh.cloud.common.mybatis.core.mapper.BaseMapperExt;
import com.zsh.cloud.common.mybatis.util.Wraps;
import com.zsh.cloud.system.application.model.query.MenuPageQuery;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysMenuDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

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
        return selectList(Wraps.<SysMenuDO>lbQ().likeIfPresent(SysMenuDO::getMenuName, menuPageQuery.getMenuName())
                .orderByAsc(SysMenuDO::getSortValue));
    }
    
    /**
     * 获取所有数据.
     *
     * @return list
     */
    @Override
    default List<SysMenuDO> selectList() {
        return selectList(Wraps.<SysMenuDO>lbQ().eq(SysMenuDO::getIsPublic, BooleanEnum.FALSE.getCode())
                .eq(SysMenuDO::getStatus, BooleanEnum.TRUE.getCode()).orderByAsc(SysMenuDO::getSortValue));
    }
    
    /**
     * 根据条件查询.
     *
     * @param menuIds
     * @param group
     * @return
     */
    default List<SysMenuDO> queryByMenuIds(Set<String> menuIds, String group) {
        return selectList(Wraps.<SysMenuDO>lbQ().in(SysMenuDO::getId, menuIds).eqIfPresent(SysMenuDO::getGroup, group)
                .orderByAsc(SysMenuDO::getSortValue));
    }
}
