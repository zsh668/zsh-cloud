package com.zsh.cloud.common.mybatis.datascope.handler;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.handler.DataPermissionHandler;
import com.zsh.cloud.common.core.constant.CommonConstant;
import com.zsh.cloud.common.core.util.RequestUtils;
import com.zsh.cloud.common.mybatis.datascope.annotations.DataScope;
import com.zsh.cloud.common.mybatis.datascope.domain.DataPermission;
import com.zsh.cloud.common.mybatis.datascope.enums.DataScopeTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.expression.operators.relational.ItemsList;
import net.sf.jsqlparser.schema.Column;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 基于组织部门数据权限规则.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/12 11:14
 */
@Slf4j
@Data
@AllArgsConstructor
public class OrgDataPermissionHandler implements DataPermissionHandler {
    
    private Function<String, DataPermission> function;
    
    @SneakyThrows
    @Override
    public Expression getSqlSegment(Expression where, String mappedStatementId) {
        try {
            Class<?> clazz = Class.forName(mappedStatementId.substring(0, mappedStatementId.lastIndexOf(".")));
            String methodName = mappedStatementId.substring(mappedStatementId.lastIndexOf(".") + 1);
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (!methodName.equals(method.getName())) {
                    continue;
                }
                // 获取自定义注解，无此注解或者忽略 则不控制数据权限
                DataScope dataScope = method.getAnnotation(DataScope.class);
                if (dataScope == null || dataScope.ignore()) {
                    continue;
                }
                // 获取到用户的id
                String userId = RequestUtils.getUserId();
                if (StringUtils.equals(CommonConstant.ADMIN_ID, userId)) {
                    return where;
                }
                DataPermission dataPermission = function.apply(userId);
                if (dataPermission == null) {
                    return where;
                }
                // 全部
                if (Objects.equals(DataScopeTypeEnum.ALL.getCode(), dataPermission.getDsType())) {
                    return where;
                } else {
                    String alias = dataScope.alias();
                    alias = StringUtils.isBlank(alias) ? "" : alias + ".";
                    // 个人
                    if (Objects.equals(DataScopeTypeEnum.SELF.getCode(), dataPermission.getDsType())) {
                        // 使用EqualsTo
                        EqualsTo equalsTo = new EqualsTo();
                        equalsTo.setLeftExpression(new Column(alias + dataPermission.getSelfScopeName()));
                        equalsTo.setRightExpression(new StringValue(userId));
                        if (where == null) {
                            return equalsTo;
                        }
                        // 创建 AND 表达式 拼接Where 和 = 表达式
                        return new AndExpression(where, equalsTo);
                    } else {
                        Set<String> orgIds = dataPermission.getOrgIds();
                        if (CollectionUtil.isEmpty(orgIds)) {
                            return where;
                        }
                        // 构建in表达式
                        InExpression inExpression = new InExpression();
                        inExpression.setLeftExpression(new Column(alias + dataPermission.getScopeName()));
                        List<Expression> conditions = orgIds.stream().map(StringValue::new)
                                .collect(Collectors.toList());
                        ItemsList itemsList = new ExpressionList(conditions);
                        inExpression.setRightItemsList(itemsList);
                        if (where == null) {
                            return inExpression;
                        }
                        return new AndExpression(where, inExpression);
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            log.error("mapper class not found");
        }
        return where;
    }
}
