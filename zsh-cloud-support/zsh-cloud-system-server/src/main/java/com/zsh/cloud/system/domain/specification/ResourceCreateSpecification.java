package com.zsh.cloud.system.domain.specification;

import com.zsh.cloud.common.core.domain.specification.AbstractSpecification;
import com.zsh.cloud.system.domain.model.resource.Resource;
import com.zsh.cloud.system.domain.model.resource.ResourceRepository;

/**
 * 资源创建Specification.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/16 10:05
 */
public class ResourceCreateSpecification extends AbstractSpecification<Resource> {
    
    private ResourceRepository resourceRepository;
    
    public ResourceCreateSpecification(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }
    
    @Override
    public boolean isSatisfiedBy(Resource resource) {
        //        List<Resource> resources = resourceRepository.find(resource.getResourceName());
        //        if (!CollectionUtils.isEmpty(resources)) {
        //            resources.forEach(o -> {
        //                // 同一资源下，名称不能重复
        //                boolean expression =
        //                        !resource.getResourceId().sameValueAs(o.getResourceId()) && resource.getParentId().sameValueAs(o.getParentId())
        //                                && resource.getResourceName().sameValueAs(o.getResourceName());
        //                Assert.notTrue(expression, ServiceErrorCode.ORG_NAME_EXISTS);
        //            });
        //        }
        //        // 父资源不能为空
        //        Resource parent = resourceRepository.find(resource.getParentId());
        //        ServiceAssert.notNull(parent, ServiceErrorCode.ORG_NOT_EXISTS.getCode(), "父资源不存在");
        //        // 同级资源
        //        resources = resourceRepository.queryList(resource.getParentId());
        //        if (CollectionUtils.isEmpty(resources)) {
        //            resources.forEach(o -> {
        //                // 同级资源下，排序不能重复
        //                boolean expression = !resource.getResourceId().sameValueAs(o.getResourceId()) && Objects.equals(resource.getSortValue(),
        //                        o.getSortValue());
        //                Assert.notTrue(expression, ServiceErrorCode.ORG_SORT_EXISTS);
        //            });
        //        }
        return true;
    }
}
