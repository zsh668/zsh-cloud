package com.zsh.cloud.system.domain.specification;

import com.zsh.cloud.common.core.domain.specification.AbstractSpecification;
import com.zsh.cloud.common.core.exception.code.enums.ServiceErrorCode;
import com.zsh.cloud.common.core.util.Assert;
import com.zsh.cloud.system.domain.model.menu.Menu;
import com.zsh.cloud.system.domain.model.menu.MenuRepository;
import com.zsh.cloud.system.domain.model.resource.Resource;
import com.zsh.cloud.system.domain.model.resource.ResourceRepository;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 资源创建Specification.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/16 10:05
 */
public class ResourceCreateSpecification extends AbstractSpecification<Resource> {
    
    private ResourceRepository resourceRepository;
    
    private MenuRepository menuRepository;
    
    public ResourceCreateSpecification(ResourceRepository resourceRepository, MenuRepository menuRepository) {
        this.resourceRepository = resourceRepository;
        this.menuRepository = menuRepository;
    }
    
    @Override
    public boolean isSatisfiedBy(Resource resource) {
        List<Resource> resources = resourceRepository.find(resource.getResourceCode());
        if (!CollectionUtils.isEmpty(resources)) {
            resources.forEach(o -> {
                // 资源编号不能重复
                boolean expression =
                        !resource.getResourceId().sameValueAs(o.getResourceId()) && resource.getResourceCode()
                                .sameValueAs(o.getResourceCode());
                Assert.notTrue(expression, ServiceErrorCode.RESOURCE_CODE_EXISTS);
            });
        }
        // 菜单不能为空
        Menu menu = menuRepository.find(resource.getMenuId());
        Assert.notNull(menu, ServiceErrorCode.MENU_NOT_EXISTS);
        // 同级资源
        resources = resourceRepository.queryList(resource.getMenuId());
        if (CollectionUtils.isEmpty(resources)) {
            resources.forEach(o -> {
                // 同级资源下，名称不能重复
                boolean expression =
                        !resource.getResourceId().sameValueAs(o.getResourceId()) && resource.getResourceName()
                                .sameValueAs(o.getResourceName());
                Assert.notTrue(expression, ServiceErrorCode.RESOURCE_NAME_EXISTS);
            });
        }
        return true;
    }
}
