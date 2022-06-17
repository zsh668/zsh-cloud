package com.zsh.cloud.system.domain.specification;

import com.zsh.cloud.common.core.domain.specification.AbstractSpecification;
import com.zsh.cloud.system.domain.model.menu.Menu;
import com.zsh.cloud.system.domain.model.menu.MenuRepository;

/**
 * 菜单创建Specification.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/16 10:05
 */
public class MenuCreateSpecification extends AbstractSpecification<Menu> {
    
    private MenuRepository menuRepository;
    
    public MenuCreateSpecification(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }
    
    @Override
    public boolean isSatisfiedBy(Menu menu) {
        //        List<Menu> menus = menuRepository.find(menu.getMenuName());
        //        if (!CollectionUtils.isEmpty(menus)) {
        //            menus.forEach(o -> {
        //                // 同一菜单下，名称不能重复
        //                boolean expression =
        //                        !menu.getMenuId().sameValueAs(o.getMenuId()) && menu.getParentId().sameValueAs(o.getParentId())
        //                                && menu.getMenuName().sameValueAs(o.getMenuName());
        //                Assert.notTrue(expression, ServiceErrorCode.ORG_NAME_EXISTS);
        //            });
        //        }
        //        // 父菜单不能为空
        //        Menu parent = menuRepository.find(menu.getParentId());
        //        ServiceAssert.notNull(parent, ServiceErrorCode.ORG_NOT_EXISTS.getCode(), "父菜单不存在");
        //        // 同级菜单
        //        menus = menuRepository.queryList(menu.getParentId());
        //        if (CollectionUtils.isEmpty(menus)) {
        //            menus.forEach(o -> {
        //                // 同级菜单下，排序不能重复
        //                boolean expression = !menu.getMenuId().sameValueAs(o.getMenuId()) && Objects.equals(menu.getSortValue(),
        //                        o.getSortValue());
        //                Assert.notTrue(expression, ServiceErrorCode.ORG_SORT_EXISTS);
        //            });
        //        }
        return true;
    }
}
