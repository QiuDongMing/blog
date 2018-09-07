package com.codermi.common.base.enums;

/**
 * @author qiudm
 * @date 2018/7/27 3:09
 * @desc
 */
public enum ErrorCode {

    FAILURE(0, "失败"),
    SUCCESS(1, "成功"),

    ERROR(100100, "服务器繁忙"),
    MISS_PARAM(100101, "缺少必填参数或参数错误"),

    USER_NOT_EXIT(100201, "账户不存在"),
    USER_DISABLED(100202, "账户被禁用");




    private Integer errorCode;
    private String  errMsg;

    ErrorCode(Integer errorCode, String errMsg) {
        this.errorCode = errorCode;
        this.errMsg = errMsg;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrMsg() {
        return errMsg;
    }
}
