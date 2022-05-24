package com.zsh.cloud.system.domain.specification;

import com.zsh.cloud.common.core.domain.specification.AbstractSpecification;
import com.zsh.cloud.common.core.exception.ServiceException;
import com.zsh.cloud.common.core.exception.code.enums.ServiceErrorCode;
import com.zsh.cloud.system.domain.model.user.User;
import com.zsh.cloud.system.domain.model.user.UserRepository;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 用户创建Specification.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/24 14:48
 */
public class UserCreateSpecification extends AbstractSpecification<User> {
    
    private UserRepository userRepository;
    
    public UserCreateSpecification(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public boolean isSatisfiedBy(User user) {
        List<User> users = userRepository.find(user.getAccount());
        if (!CollectionUtils.isEmpty(users)) {
            users.forEach(u -> {
                if (user.getTenantId().sameValueAs(user.getTenantId())) {
                    throw new ServiceException(ServiceErrorCode.USER_ACCOUNT_EXISTS);
                }
            });
        }
        return true;
    }
}
