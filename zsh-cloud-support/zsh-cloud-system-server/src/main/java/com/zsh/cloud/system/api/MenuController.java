package com.zsh.cloud.system.api;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.common.log.annotations.SysLog;
import com.zsh.cloud.common.web.translate.Translator;
import com.zsh.cloud.system.application.MenuApplicationService;
import com.zsh.cloud.system.application.MenuQueryService;
import com.zsh.cloud.system.application.command.IdsCommand;
import com.zsh.cloud.system.application.command.MenuCreateCommand;
import com.zsh.cloud.system.application.command.MenuUpdateCommand;
import com.zsh.cloud.system.application.dto.MenuDTO;
import com.zsh.cloud.system.application.dto.MenuTreeDTO;
import com.zsh.cloud.system.application.query.MenuPageQuery;
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
 * 菜单管理.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/29 14:10
 */
@Api(tags = "菜单管理")
@Slf4j
@RestController
public class MenuController {
    
    @Autowired
    private MenuQueryService menuQueryService;
    
    @Autowired
    private MenuApplicationService menuApplicationService;
    
    /**
     * 查询菜单树.
     *
     * @param menuPageQuery
     * @return
     */
    @ApiOperation("分页查询菜单")
    @Translator
    @GetMapping("menus/tree")
    public Page<MenuTreeDTO> tree(MenuPageQuery menuPageQuery) {
        return menuQueryService.queryList(menuPageQuery);
    }
    
    /**
     * 根据ID查询菜单.
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "查询菜单", notes = "查询菜单")
    @Translator
    @GetMapping("menus/{id}")
    public MenuDTO get(@PathVariable String id) {
        return menuQueryService.find(id);
    }
    
    /**
     * 保存菜单.
     *
     * @param menuCommand
     * @return
     */
    @ApiOperation("保存菜单")
    @SysLog("保存菜单")
    @PostMapping("menus")
    public Boolean save(@Valid @RequestBody MenuCreateCommand menuCommand) {
        menuApplicationService.save(menuCommand);
        return Boolean.TRUE;
    }
    
    /**
     * 修改菜单.
     *
     * @param menuCommand
     * @return
     */
    @ApiOperation("修改菜单")
    @SysLog("修改菜单")
    @PutMapping("menus/{id}")
    public Boolean update(@Valid @RequestBody MenuUpdateCommand menuCommand) {
        menuApplicationService.update(menuCommand);
        return Boolean.TRUE;
    }
    
    /**
     * 删除菜单.
     *
     * @param command
     * @return
     */
    @ApiOperation("删除菜单")
    @SysLog("删除菜单")
    @DeleteMapping("menus")
    public Boolean delete(@Valid @RequestBody IdsCommand command) {
        menuApplicationService.deleteBatch(command.getIds());
        return Boolean.TRUE;
    }
    
    /**
     * 开启、禁用菜单.
     *
     * @param id
     * @return
     */
    @ApiOperation("开启、禁用菜单")
    @SysLog("开启、禁用菜单")
    @PutMapping("menus/disable")
    public Boolean disable(String id) {
        menuApplicationService.disable(id);
        return Boolean.TRUE;
    }
}
