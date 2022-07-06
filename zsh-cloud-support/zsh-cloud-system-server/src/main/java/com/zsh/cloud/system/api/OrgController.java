package com.zsh.cloud.system.api;

import com.zsh.cloud.common.log.annotations.SysLog;
import com.zsh.cloud.common.web.translate.Translator;
import com.zsh.cloud.system.application.OrgApplicationService;
import com.zsh.cloud.system.application.OrgQueryService;
import com.zsh.cloud.system.application.command.IdsCommand;
import com.zsh.cloud.system.application.command.OrgCreateCommand;
import com.zsh.cloud.system.application.command.OrgUpdateCommand;
import com.zsh.cloud.system.application.dto.OrgDTO;
import com.zsh.cloud.system.application.dto.OrgTreeDTO;
import com.zsh.cloud.system.application.query.OrgPageQuery;
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
 * 组织管理.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/29 14:03
 */
@Api(tags = "组织管理")
@Slf4j
@RestController
public class OrgController {
    
    @Autowired
    private OrgQueryService orgQueryService;
    
    @Autowired
    private OrgApplicationService orgApplicationService;
    
    /**
     * 查询组织树.
     *
     * @param orgPageQuery
     * @return
     */
    @ApiOperation("查询组织树")
    @SysLog("查询组织树")
    @Translator
    @GetMapping("orgs/tree")
    public List<OrgTreeDTO> tree(OrgPageQuery orgPageQuery) {
        return orgQueryService.queryList(orgPageQuery);
    }
    
    /**
     * 根据ID查询组织.
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "查询组织", notes = "查询组织")
    @SysLog("根据ID查询组织")
    @Translator
    @GetMapping("orgs/{id}")
    public OrgDTO get(@PathVariable String id) {
        return orgQueryService.find(id);
    }
    
    /**
     * 保存组织.
     *
     * @param orgCommand
     * @return
     */
    @ApiOperation("保存组织")
    @SysLog("保存组织")
    @PostMapping("orgs")
    public Boolean save(@Valid @RequestBody OrgCreateCommand orgCommand) {
        orgApplicationService.save(orgCommand);
        return Boolean.TRUE;
    }
    
    /**
     * 修改组织.
     *
     * @param orgCommand
     * @return
     */
    @ApiOperation("修改组织")
    @SysLog("修改组织")
    @PutMapping("orgs")
    public Boolean update(@Valid @RequestBody OrgUpdateCommand orgCommand) {
        orgApplicationService.update(orgCommand);
        return Boolean.TRUE;
    }
    
    /**
     * 删除组织.
     *
     * @param command
     * @return
     */
    @ApiOperation("删除组织")
    @SysLog("删除组织")
    @DeleteMapping("orgs")
    public Boolean delete(@Valid @RequestBody IdsCommand command) {
        orgApplicationService.deleteBatch(command.getIds());
        return Boolean.TRUE;
    }
    
    /**
     * 开启、禁用组织.
     *
     * @param id
     * @return
     */
    @ApiOperation("开启、禁用组织")
    @SysLog("开启、禁用组织")
    @PutMapping("orgs/disable/{id}")
    public Boolean disable(@PathVariable String id) {
        orgApplicationService.disable(id);
        return Boolean.TRUE;
    }
}
