package com.zsh.cloud.system.api;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.common.log.annotations.SysLog;
import com.zsh.cloud.system.application.OptLogApplicationService;
import com.zsh.cloud.system.application.OptLogQueryService;
import com.zsh.cloud.common.web.model.IdsCommand;
import com.zsh.cloud.system.application.model.dto.OptLogInfoDTO;
import com.zsh.cloud.system.application.model.dto.OptLogPageDTO;
import com.zsh.cloud.system.application.model.query.OptLogPageQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 操作日志管理.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/7 10:04
 */
@Api(tags = "操作日志管理")
@Slf4j
@RestController
public class OptLogController {
    
    @Autowired
    private OptLogQueryService optLogQueryService;
    
    @Autowired
    private OptLogApplicationService optLogApplicationService;
    
    /**
     * 分页查询操作日志.
     *
     * @param optLogPageQuery
     * @return
     */
    @ApiOperation("分页查询操作日志")
    @SysLog("分页查询操作日志")
    @GetMapping("optLogs")
    public Page<OptLogPageDTO> page(OptLogPageQuery optLogPageQuery) {
        return optLogQueryService.queryPage(optLogPageQuery);
    }
    
    /**
     * 根据ID查询操作日志.
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "查询操作日志", notes = "查询操作日志")
    @SysLog("根据ID查询操作日志")
    @GetMapping("optLogs/{id}")
    public OptLogInfoDTO get(@PathVariable String id) {
        return optLogQueryService.find(id);
    }
    
    /**
     * 删除操作日志.
     *
     * @param command
     * @return
     */
    @ApiOperation("删除操作日志")
    @SysLog("删除操作日志")
    @DeleteMapping("optLogs")
    public Boolean delete(@Valid @RequestBody IdsCommand command) {
        optLogApplicationService.deleteBatch(command.getIds());
        return Boolean.TRUE;
    }
}
