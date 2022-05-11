package com.zsh.cloud.common.core.domain;

import com.zsh.cloud.common.core.exception.code.enums.GlobalErrorCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * 通用返回
 *
 * @author hang
 * @version 1.0
 * @date 2022/03/14 10:22
 */
@Data
@ApiModel(value = "返回实体")
public class Result<T> implements Serializable {
    
    /**
     * 响应码
     */
    @ApiModelProperty(name = "code", dataType = "int", value = "响应编码:0/200-请求处理成功")
    private int code;
    
    /**
     * 返回数据
     */
    @ApiModelProperty(name = "data", dataType = "object", value = "数据内容")
    private T data;
    
    /**
     * 响应信息
     */
    @ApiModelProperty(name = "msg", dataType = "string", value = "响应信息")
    private String msg;
    
    /**
     * 无参构造器.
     */
    public Result() {
        this.code = GlobalErrorCode.SUCCESS.getCode();
        this.msg = GlobalErrorCode.SUCCESS.getMsg();
    }
    
    /**
     * 构造器.
     */
    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    
    /**
     * 全参构造器.
     */
    public Result(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }
    
    /**
     * 成功
     *
     * @return com.zsh.cloud.common.core.pojo.Result<T>
     * @author hang
     * @date 2022/03/14 10:29
     */
    public static Result<Boolean> success() {
        return success(Boolean.TRUE);
    }
    
    /**
     * 成功
     *
     * @param data
     * @return com.zsh.cloud.common.core.pojo.Result<T>
     * @author hang
     * @date 2022/03/14 10:29
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(GlobalErrorCode.SUCCESS.getCode(), data, GlobalErrorCode.SUCCESS.getMsg());
    }
    
    /**
     * 失败
     *
     * @param message
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(String message) {
        return error(GlobalErrorCode.INTERNAL_SERVER_ERROR.getCode(), message);
    }
    
    /**
     * 失败
     *
     * @param code
     * @param message
     * @return com.zsh.cloud.common.core.pojo.Result<T>
     * @author hang
     * @date 2022/03/14 10:30
     */
    public static <T> Result<T> error(int code, String message) {
        return new Result<>(code, message);
    }
    
    /**
     * 逻辑处理是否成功
     *
     * @return 是否成功
     */
    public Boolean getIsSuccess() {
        return this.code == GlobalErrorCode.SUCCESS.getCode() || this.code == HttpStatus.OK.value();
    }
    
    /**
     * 请求超时.
     *
     * @param <T>
     * @return
     */
    public static <T> Result<T> timeout() {
        return error(GlobalErrorCode.GATEWAY_TIMEOUT.getCode(), GlobalErrorCode.GATEWAY_TIMEOUT.getMsg());
    }
}
