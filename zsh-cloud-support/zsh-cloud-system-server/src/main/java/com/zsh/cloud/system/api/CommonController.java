package com.zsh.cloud.system.api;

import com.zsh.cloud.common.log.annotations.SysLog;
import com.zsh.cloud.system.api.dubbo.CommonDubboService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 公共接口.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/29 14:03
 */
@Api(tags = "公共接口")
@Slf4j
@RestController
public class CommonController {
    
    @DubboReference
    private CommonDubboService commonDubboService;
    
    /**
     * 生成code唯一码.
     *
     * @return
     */
    @ApiOperation(value = "生成code唯一码", notes = "生成code唯一码")
    @SysLog("生成code唯一码")
    @GetMapping("commons/getCode")
    public String getCode() {
        return commonDubboService.getCode();
    }
}
