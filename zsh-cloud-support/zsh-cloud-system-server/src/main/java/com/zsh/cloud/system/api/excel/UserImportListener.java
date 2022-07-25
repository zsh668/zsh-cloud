package com.zsh.cloud.system.api.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.ListUtils;
import com.zsh.cloud.common.core.domain.IDict;
import com.zsh.cloud.common.core.exception.code.enums.ServiceErrorCode;
import com.zsh.cloud.common.core.util.ServiceAssert;
import com.zsh.cloud.common.core.util.ValidatorUtils;
import com.zsh.cloud.common.web.excel.imports.ImportResultDTO;
import com.zsh.cloud.system.application.UserManageService;
import com.zsh.cloud.system.application.model.command.UserCreateCommand;
import com.zsh.cloud.system.application.model.command.UserImportExcelCommand;
import com.zsh.cloud.system.domain.model.org.Org;
import com.zsh.cloud.system.domain.model.role.Role;
import com.zsh.cloud.system.domain.model.station.Station;
import com.zsh.cloud.system.domain.model.user.GenderEnum;
import com.zsh.cloud.system.domain.model.user.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * 批量导入用户.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/20 11:36
 */
@Slf4j
public class UserImportListener extends AnalysisEventListener<UserImportExcelCommand> {
    
    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list，方便内存回收.
     */
    private static final int BATCH_COUNT = 1000;
    
    private final List<UserCreateCommand> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
    
    /**
     * 存储表格数据
     */
    private final Map<String, Integer> cachedDataMap = new HashMap<>(BATCH_COUNT);
    
    /**
     * 存储错误信息.
     */
    private final TreeMap<Integer, String> resultMap = new TreeMap<>();
    
    /**
     * 行数.
     */
    AtomicInteger row = new AtomicInteger(1);
    
    private final ImportResultDTO importResult;
    
    private final UserManageService userManageService;
    
    public UserImportListener(ImportResultDTO importResult, UserManageService userManageService) {
        this.importResult = importResult;
        this.userManageService = userManageService;
    }
    
    @Override
    public void invoke(UserImportExcelCommand userCommand, AnalysisContext context) {
        log.info("解析到一条数据:{}", userCommand);
        int index = context.readRowHolder().getRowIndex() + 1;
        String message = ValidatorUtils.validateEntity(userCommand);
        ServiceAssert.notTrue(StringUtils.isNotBlank(message), ServiceErrorCode.USER_VERIFICATION_ERROR.getCode(),
                message);
        String key = userCommand.getAccount();
        ServiceAssert.notTrue(cachedDataMap.containsKey(key), ServiceErrorCode.USER_VERIFICATION_ERROR.getCode(),
                "该账号与第" + cachedDataMap.get(key) + "行重复");
        cachedDataMap.put(key, index);
        UserCreateCommand command = new UserCreateCommand();
        command.setUserName(userCommand.getUserName());
        command.setEmail(userCommand.getEmail());
        command.setMobile(userCommand.getMobile());
        // 校验账号
        User user = userManageService.findUser(userCommand.getAccount());
        ServiceAssert.notTrue(user != null, ServiceErrorCode.USER_VERIFICATION_ERROR.getCode(), "账号存在");
        command.setAccount(userCommand.getAccount());
        // 校验组织
        Org org = userManageService.findOrg(userCommand.getOrgName());
        ServiceAssert.notTrue(org == null, ServiceErrorCode.USER_VERIFICATION_ERROR.getCode(), "组织不存在");
        command.setOrgId(org.getOrgId().getId());
        // 校验岗位
        Station station = userManageService.findStation(userCommand.getStationName());
        ServiceAssert.notTrue(station == null, ServiceErrorCode.USER_VERIFICATION_ERROR.getCode(), "岗位不存在");
        command.setStationId(station.getStationId().getId());
        // 校验性别
        Integer gender = IDict.getCodeByText(GenderEnum.class, userCommand.getGenderDesc());
        ServiceAssert.notTrue(gender == null, ServiceErrorCode.USER_VERIFICATION_ERROR.getCode(), "性别不正确");
        command.setGender(gender);
        // 校验角色
        if (StringUtils.isNotBlank(userCommand.getRoleNames())) {
            String[] roleNames = userCommand.getRoleNames().split(",");
            List<Role> roles = userManageService.findRole(roleNames);
            ServiceAssert.notTrue(CollectionUtils.isEmpty(roles), ServiceErrorCode.USER_VERIFICATION_ERROR.getCode(),
                    "角色不存在");
            Set<String> roleIds = roles.stream().map(role -> role.getRoleId().getId()).collect(Collectors.toSet());
            List<Role> roleList = roles.stream()
                    .filter(role -> role.getRepel() != null && roleIds.contains(role.getRepel().getId()))
                    .collect(Collectors.toList());
            ServiceAssert.isTrue(CollectionUtils.isEmpty(roleList), ServiceErrorCode.USER_VERIFICATION_ERROR.getCode(),
                    "存在互斥角色");
            command.setRoleIds(roleIds);
        }
        cachedDataList.add(command);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (cachedDataList.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            cachedDataList.clear();
        }
    }
    
    @Override
    public void onException(Exception exception, AnalysisContext context) throws Exception {
        int index = context.readRowHolder().getRowIndex() + 1;
        resultMap.put(index, "第 " + index + " 行 " + exception.getMessage());
    }
    
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("批量保存数据:{}", cachedDataList);
        saveData();
        importResult.setTotal(importResult.getTotal() + cachedDataList.size()).setFail(resultMap.values().size())
                .setSuccess(importResult.getTotal() - importResult.getFail()).setMessage(resultMap.values());
        cachedDataList.clear();
        log.info("所有数据解析完成！");
    }
    
    /**
     * 加上存储数据库
     */
    private void saveData() {
        log.info("{}条数据，开始存储数据库！", cachedDataList.size());
        cachedDataList.forEach(userCommand -> {
            row.incrementAndGet();
            try {
                userManageService.save(userCommand);
            } catch (Exception e) {
                resultMap.put(row.get(), "第 " + row.get() + " 行 导入失败");
            }
        });
        log.info("存储数据库成功！");
    }
}
