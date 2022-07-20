package com.zsh.cloud.system.api.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.ListUtils;
import com.zsh.cloud.common.web.excel.imports.ImportResultDTO;
import com.zsh.cloud.system.application.model.command.UserImportExcelCommand;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.TreeMap;

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
    
    private final List<UserImportExcelCommand> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
    
    private final ImportResultDTO importResult;
    
    public UserImportListener(ImportResultDTO importResult) {
        this.importResult = importResult;
    }
    
    @Override
    public void invoke(UserImportExcelCommand data, AnalysisContext context) {
        log.info("解析到一条数据:{}", data);
        cachedDataList.add(data);
    }
    
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("批量保存数据:{}", cachedDataList);
        TreeMap<Integer, String> resultMap = new TreeMap<>();
        resultMap.put(1, "账号不能为空");
        resultMap.put(1, "姓名不能为空");
        importResult.setTotal(importResult.getTotal() + cachedDataList.size()).setSuccess(1).setFail(resultMap.size())
                .setMessage(resultMap.values());
        cachedDataList.clear();
        log.info("所有数据解析完成！");
    }
}
