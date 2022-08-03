package com.zsh.cloud.system.api;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.common.log.annotations.SysLog;
import com.zsh.cloud.common.web.translate.Translator;
import com.zsh.cloud.system.application.TenantApplicationService;
import com.zsh.cloud.system.application.TenantQueryService;
import com.zsh.cloud.common.web.model.IdsCommand;
import com.zsh.cloud.system.application.model.command.TenantCreateCommand;
import com.zsh.cloud.system.application.model.command.TenantUpdateCommand;
import com.zsh.cloud.system.application.model.dto.TenantDTO;
import com.zsh.cloud.system.application.model.dto.TenantPageDTO;
import com.zsh.cloud.system.application.model.query.TenantPageQuery;
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
import java.util.List;

/**
 * 租户管理.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/29 13:58
 */
@Api(tags = "租户管理")
@Slf4j
@RestController
public class TenantController {
    
    @Autowired
    private TenantQueryService tenantQueryService;
    
    @Autowired
    private TenantApplicationService tenantApplicationService;
    
    /**
     * 分页查询租户.
     *
     * @param tenantPageQuery
     * @return
     */
    @ApiOperation("分页查询租户")
    @SysLog("分页查询租户")
    @Translator
    @GetMapping("tenants")
    public Page<TenantPageDTO> page(TenantPageQuery tenantPageQuery) {
        return tenantQueryService.queryPage(tenantPageQuery);
    }
    
    /**
     * 查询租户列表.
     *
     * @param tenantPageQuery
     * @return
     */
    @ApiOperation("查询租户列表")
    @SysLog("查询租户列表")
    @Translator
    @GetMapping("tenants/list")
    public List<TenantPageDTO> list(TenantPageQuery tenantPageQuery) {
        return tenantQueryService.queryList(tenantPageQuery);
    }
    
    /**
     * 根据ID查询租户.
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "查询租户", notes = "查询租户")
    @SysLog("根据ID查询租户")
    @Translator
    @GetMapping("tenants/{id}")
    public TenantDTO get(@PathVariable String id) {
        return tenantQueryService.find(id);
    }
    
    /**
     * 保存租户.
     *
     * @param tenantCommand
     * @return
     */
    @ApiOperation("保存租户")
    @SysLog("保存租户")
    @PostMapping("tenants")
    public Boolean save(@Valid @RequestBody TenantCreateCommand tenantCommand) {
        tenantApplicationService.save(tenantCommand);
        return Boolean.TRUE;
    }
    
    /**
     * 修改租户.
     *
     * @param tenantCommand
     * @return
     */
    @ApiOperation("修改租户")
    @SysLog("修改租户")
    @PutMapping("tenants")
    public Boolean update(@Valid @RequestBody TenantUpdateCommand tenantCommand) {
        tenantApplicationService.update(tenantCommand);
        return Boolean.TRUE;
    }
    
    /**
     * 删除租户.
     *
     * @param command
     * @return
     */
    @ApiOperation("删除租户")
    @SysLog("删除租户")
    @DeleteMapping("tenants")
    public Boolean delete(@Valid @RequestBody IdsCommand command) {
        tenantApplicationService.deleteBatch(command.getIds());
        return Boolean.TRUE;
    }
    
    /**
     * 开启、禁用租户.
     *
     * @param id
     * @return
     */
    @ApiOperation("开启、禁用租户")
    @SysLog("开启、禁用租户")
    @PutMapping("tenants/disable/{id}")
    public Boolean disable(@PathVariable String id) {
        tenantApplicationService.disable(id);
        return Boolean.TRUE;
    }
}
