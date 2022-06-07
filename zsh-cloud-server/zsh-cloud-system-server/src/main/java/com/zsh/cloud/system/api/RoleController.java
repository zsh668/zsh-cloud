package com.zsh.cloud.system.api;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.common.log.annotations.SysLog;
import com.zsh.cloud.common.web.translate.Translator;
import com.zsh.cloud.system.application.RoleApplicationService;
import com.zsh.cloud.system.application.RoleQueryService;
import com.zsh.cloud.system.application.command.IdsCommand;
import com.zsh.cloud.system.application.command.RoleCreateCommand;
import com.zsh.cloud.system.application.command.RoleUpdateCommand;
import com.zsh.cloud.system.application.dto.RoleDTO;
import com.zsh.cloud.system.application.dto.RolePageDTO;
import com.zsh.cloud.system.application.query.RolePageQuery;
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
 * 角色管理.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/29 14:00
 */
@Api(tags = "角色管理")
@Slf4j
@RestController
public class RoleController {
    
    @Autowired
    private RoleQueryService roleQueryService;
    
    @Autowired
    private RoleApplicationService roleApplicationService;
    
    /**
     * 角色分页查询.
     *
     * @param rolePageQuery
     * @return
     */
    @ApiOperation("分页查询角色")
    @Translator
    @GetMapping("roles")
    public Page<RolePageDTO> page(RolePageQuery rolePageQuery) {
        return roleQueryService.queryPage(rolePageQuery);
    }
    
    /**
     * 根据ID查询角色.
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "查询角色", notes = "查询角色")
    @GetMapping("roles/{id}")
    public RoleDTO get(@PathVariable String id) {
        return roleQueryService.find(id);
    }
    
    /**
     * 保存角色.
     *
     * @param roleCommand
     * @return
     */
    @ApiOperation("保存角色")
    @SysLog("保存角色")
    @PostMapping("roles")
    public Boolean save(@Valid @RequestBody RoleCreateCommand roleCommand) {
        roleApplicationService.save(roleCommand);
        return Boolean.TRUE;
    }
    
    /**
     * 修改角色.
     *
     * @param roleCommand
     * @return
     */
    @ApiOperation("修改角色")
    @SysLog("修改角色")
    @PutMapping("roles/{id}")
    public Boolean update(@Valid @RequestBody RoleUpdateCommand roleCommand) {
        roleApplicationService.update(roleCommand);
        return Boolean.TRUE;
    }
    
    /**
     * 删除角色.
     *
     * @param command
     * @return
     */
    @ApiOperation("删除角色")
    @SysLog("删除角色")
    @DeleteMapping("roles")
    public Boolean delete(@Valid @RequestBody IdsCommand command) {
        roleApplicationService.deleteBatch(command.getIds());
        return Boolean.TRUE;
    }
    
    /**
     * 开启、禁用角色.
     *
     * @param id
     * @return
     */
    @ApiOperation("开启、禁用角色")
    @SysLog("开启、禁用角色")
    @PutMapping("roles/disable")
    public Boolean disable(String id) {
        roleApplicationService.disable(id);
        return Boolean.TRUE;
    }
}
