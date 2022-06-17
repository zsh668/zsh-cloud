package com.zsh.cloud.system.api;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.common.log.annotations.SysLog;
import com.zsh.cloud.common.web.translate.Translator;
import com.zsh.cloud.system.application.ResourceApplicationService;
import com.zsh.cloud.system.application.ResourceQueryService;
import com.zsh.cloud.system.application.command.IdsCommand;
import com.zsh.cloud.system.application.command.ResourceCreateCommand;
import com.zsh.cloud.system.application.command.ResourceUpdateCommand;
import com.zsh.cloud.system.application.dto.ResourceDTO;
import com.zsh.cloud.system.application.dto.ResourcePageDTO;
import com.zsh.cloud.system.application.query.ResourcePageQuery;
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
 * 资源管理.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/17 14:57
 */
@Api(tags = "资源管理")
@Slf4j
@RestController
public class ResourceController {
    
    @Autowired
    private ResourceQueryService resourceQueryService;
    
    @Autowired
    private ResourceApplicationService resourceApplicationService;
    
    /**
     * 分页查询资源.
     *
     * @param resourcePageQuery
     * @return
     */
    @ApiOperation("分页查询资源")
    @Translator
    @GetMapping("resources")
    public Page<ResourcePageDTO> page(ResourcePageQuery resourcePageQuery) {
        return resourceQueryService.queryPage(resourcePageQuery);
    }
    
    /**
     * 根据ID查询资源.
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "查询资源", notes = "查询资源")
    @Translator
    @GetMapping("resources/{id}")
    public ResourceDTO get(@PathVariable String id) {
        return resourceQueryService.find(id);
    }
    
    /**
     * 保存资源.
     *
     * @param resourceCommand
     * @return
     */
    @ApiOperation("保存资源")
    @SysLog("保存资源")
    @PostMapping("resources")
    public Boolean save(@Valid @RequestBody ResourceCreateCommand resourceCommand) {
        resourceApplicationService.save(resourceCommand);
        return Boolean.TRUE;
    }
    
    /**
     * 修改资源.
     *
     * @param resourceCommand
     * @return
     */
    @ApiOperation("修改资源")
    @SysLog("修改资源")
    @PutMapping("resources/{id}")
    public Boolean update(@Valid @RequestBody ResourceUpdateCommand resourceCommand) {
        resourceApplicationService.update(resourceCommand);
        return Boolean.TRUE;
    }
    
    /**
     * 删除资源.
     *
     * @param command
     * @return
     */
    @ApiOperation("删除资源")
    @SysLog("删除资源")
    @DeleteMapping("resources")
    public Boolean delete(@Valid @RequestBody IdsCommand command) {
        resourceApplicationService.deleteBatch(command.getIds());
        return Boolean.TRUE;
    }
    
    /**
     * 开启、禁用资源.
     *
     * @param id
     * @return
     */
    @ApiOperation("开启、禁用资源")
    @SysLog("开启、禁用资源")
    @PutMapping("resources/disable")
    public Boolean disable(String id) {
        resourceApplicationService.disable(id);
        return Boolean.TRUE;
    }
}
