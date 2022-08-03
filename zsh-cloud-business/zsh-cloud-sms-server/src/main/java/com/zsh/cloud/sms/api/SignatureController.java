package com.zsh.cloud.sms.api;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.common.log.annotations.SysLog;
import com.zsh.cloud.common.web.excel.export.ExportExcel;
import com.zsh.cloud.common.web.model.IdsCommand;
import com.zsh.cloud.common.web.translate.Translator;
import com.zsh.cloud.sms.application.SignatureApplicationService;
import com.zsh.cloud.sms.application.SignatureQueryService;
import com.zsh.cloud.sms.application.model.command.SignatureCreateCommand;
import com.zsh.cloud.sms.application.model.command.SignatureUpdateCommand;
import com.zsh.cloud.sms.application.model.dto.SignatureDTO;
import com.zsh.cloud.sms.application.model.dto.SignaturePageDTO;
import com.zsh.cloud.sms.application.model.query.SignaturePageQuery;
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
 * 签名管理.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/8/1 16:29
 */
@Api(tags = "签名管理")
@Slf4j
@RestController
public class SignatureController {
    
    @Autowired
    private SignatureQueryService signatureQueryService;
    
    @Autowired
    private SignatureApplicationService signatureApplicationService;
    
    /**
     * 分页查询签名.
     *
     * @param signaturePageQuery
     * @return
     */
    @ApiOperation("分页查询签名")
    @SysLog("分页查询签名")
    @Translator
    @ExportExcel(fileName = "签名")
    @GetMapping("signatures")
    public Page<SignaturePageDTO> page(SignaturePageQuery signaturePageQuery) {
        return signatureQueryService.queryPage(signaturePageQuery);
    }
    
    /**
     * 查询签名列表.
     *
     * @param signaturePageQuery
     * @return
     */
    @ApiOperation("查询签名列表")
    @SysLog("查询签名列表")
    @Translator
    @GetMapping("signatures/list")
    public List<SignaturePageDTO> list(SignaturePageQuery signaturePageQuery) {
        return signatureQueryService.queryList(signaturePageQuery);
    }
    
    /**
     * 根据ID查询签名.
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "查询签名", notes = "查询签名")
    @SysLog("根据ID查询签名")
    @Translator
    @GetMapping("signatures/{id}")
    public SignatureDTO get(@PathVariable String id) {
        return signatureQueryService.find(id);
    }
    
    /**
     * 保存签名.
     *
     * @param signatureCommand
     * @return
     */
    @ApiOperation("保存签名")
    @SysLog("保存签名")
    @PostMapping("signatures")
    public Boolean save(@Valid @RequestBody SignatureCreateCommand signatureCommand) {
        signatureApplicationService.save(signatureCommand);
        return Boolean.TRUE;
    }
    
    /**
     * 修改签名.
     *
     * @param signatureCommand
     * @return
     */
    @ApiOperation("修改签名")
    @SysLog("修改签名")
    @PutMapping("signatures")
    public Boolean update(@Valid @RequestBody SignatureUpdateCommand signatureCommand) {
        signatureApplicationService.update(signatureCommand);
        return Boolean.TRUE;
    }
    
    /**
     * 删除签名.
     *
     * @param command
     * @return
     */
    @ApiOperation("删除签名")
    @SysLog("删除签名")
    @DeleteMapping("signatures")
    public Boolean delete(@Valid @RequestBody IdsCommand command) {
        signatureApplicationService.deleteBatch(command.getIds());
        return Boolean.TRUE;
    }
}
