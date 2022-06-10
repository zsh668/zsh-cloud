package com.zsh.cloud.system.domain.specification;

import com.zsh.cloud.common.core.domain.specification.AbstractSpecification;
import com.zsh.cloud.common.core.exception.code.enums.ServiceErrorCode;
import com.zsh.cloud.common.core.util.ServiceAssert;
import com.zsh.cloud.system.domain.model.user.User;
import com.zsh.cloud.system.domain.model.user.UserId;
import com.zsh.cloud.system.domain.model.user.UserRepository;

import java.util.List;

/**
 * 用户修改Specification.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/24 14:48
 */
public class UserUpdateSpecification extends AbstractSpecification<User> {
    
    private UserRepository userRepository;
    
    public UserUpdateSpecification(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public boolean isSatisfiedBy(User user) {
        User userDb = userRepository.find(user.getUserId());
        if (!user.getSuperior().sameValueAs(userDb.getSuperior())) {
            checkSuperior(user.getSuperior(), user.getUserId());
        }
        return true;
    }
    
    /**
     * 检查上级.
     *
     * @param superiorId
     * @param userId
     */
    private void checkSuperior(UserId superiorId, UserId userId) {
        ServiceAssert.notTrue(userId.sameValueAs(superiorId), ServiceErrorCode.USER_VERIFICATION_ERROR.getCode(),
                "直属上级已经是当前用户的下属");
        List<User> users = userRepository.findBySuperior(userId);
        users.forEach(user -> {
            ServiceAssert.notTrue(user.getUserId().sameValueAs(superiorId),
                    ServiceErrorCode.USER_VERIFICATION_ERROR.getCode(), "直属上级已经是当前用户的下属");
            // 递归判断.
            checkSuperior(superiorId, user.getUserId());
        });
    }
}
