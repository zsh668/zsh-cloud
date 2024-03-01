package com.zsh.cloud.system.api;

import com.zsh.cloud.common.oss.service.OssTemplate;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文件管理.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2024/3/1 14:19
 */
@Api(tags = "文件管理")
@Slf4j
@RestController
public class FileController {
    
    @Autowired
    private OssTemplate ossTemplate;
}
