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
    MISS_PARAM(100101, "缺少必填参数或参数错误");














    private Integer errorCode;
    private String  desc;

    private ErrorCode(Integer errorCode, String desc) {
        this.errorCode = errorCode;
        this.desc = desc;
    }

    public Integer getErrorCode() {
        return errorCode;
    }
}
