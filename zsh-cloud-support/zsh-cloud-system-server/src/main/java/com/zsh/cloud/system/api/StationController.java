package com.zsh.cloud.system.api;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.common.log.annotations.SysLog;
import com.zsh.cloud.common.web.translate.Translator;
import com.zsh.cloud.system.application.StationApplicationService;
import com.zsh.cloud.system.application.StationQueryService;
import com.zsh.cloud.system.application.command.IdsCommand;
import com.zsh.cloud.system.application.command.StationCreateCommand;
import com.zsh.cloud.system.application.command.StationUpdateCommand;
import com.zsh.cloud.system.application.dto.StationDTO;
import com.zsh.cloud.system.application.dto.StationPageDTO;
import com.zsh.cloud.system.application.query.StationPageQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 岗位管理.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/29 14:04
 */
@Api(tags = "岗位管理")
@Slf4j
@RestController
public class StationController {
    
    @Autowired
    private StationQueryService stationQueryService;
    
    @Autowired
    private StationApplicationService stationApplicationService;
    
    /**
     * 分页查询岗位.
     *
     * @param stationPageQuery
     * @return
     */
    @ApiOperation("分页查询岗位")
    @Translator
    @GetMapping("stations")
    public Page<StationPageDTO> page(StationPageQuery stationPageQuery) {
        return stationQueryService.queryPage(stationPageQuery);
    }
    
    /**
     * 根据ID查询岗位.
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "查询岗位", notes = "查询岗位")
    @Translator
    @GetMapping("stations/{id}")
    public StationDTO get(@PathVariable String id) {
        return stationQueryService.find(id);
    }
    
    /**
     * 保存岗位.
     *
     * @param stationCommand
     * @return
     */
    @ApiOperation("保存岗位")
    @SysLog("保存岗位")
    @PostMapping("stations")
    public Boolean save(@Valid @RequestBody StationCreateCommand stationCommand) {
        stationApplicationService.save(stationCommand);
        return Boolean.TRUE;
    }
    
    /**
     * 修改岗位.
     *
     * @param stationCommand
     * @return
     */
    @ApiOperation("修改岗位")
    @SysLog("修改岗位")
    @PutMapping("stations/{id}")
    public Boolean update(@Valid @RequestBody StationUpdateCommand stationCommand) {
        stationApplicationService.update(stationCommand);
        return Boolean.TRUE;
    }
    
    /**
     * 删除岗位.
     *
     * @param command
     * @return
     */
    @ApiOperation("删除岗位")
    @SysLog("删除岗位")
    @DeleteMapping("stations")
    public Boolean delete(@Valid @RequestBody IdsCommand command) {
        stationApplicationService.deleteBatch(command.getIds());
        return Boolean.TRUE;
    }
    
    /**
     * 开启、禁用岗位.
     *
     * @param id
     * @return
     */
    @ApiOperation("开启、禁用岗位")
    @SysLog("开启、禁用岗位")
    @PutMapping("stations/disable")
    public Boolean disable(String id) {
        stationApplicationService.disable(id);
        return Boolean.TRUE;
    }
}
