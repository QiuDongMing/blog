package com.codermi.common.base.utils;

import com.alibaba.fastjson.JSONObject;
import com.codermi.common.base.enums.ErrorCode;

/**
 * @author qiudm
 * @date 2018/6/28 10:55
 * @desc
 */
public class JsonResult extends JSONObject {
    private static final long serialVersionUID = 1L;

    public JsonResult(){}

    public JsonResult(int resultCode, String resultMsg) {
        setResultCode(resultCode);
        setResultMsg(resultMsg);
    }

    public JsonResult(int resultCode, String resultMsg, Object data) {
        setResultCode(resultCode);
        setResultMsg(resultMsg);
        setData(data);
    }

    public JsonResult(int resultCode, String resultMsg, String detailMsg) {
        setResultCode(resultCode);
        setResultMsg(resultMsg);
        setDetailMsg(detailMsg);
    }

    public JsonResult(int resultCode, String resultMsg, String detailMsg, Object data) {
        setResultCode(resultCode);
        setResultMsg(resultMsg);
        setDetailMsg(detailMsg);
        setData(data);
    }




    public static JsonResult SUCCESS() {
        return SUCCESS(null);
    }

    public static JsonResult SUCCESS(String resultMsg, Object data) {
        return new JsonResult(ErrorCode.SUCCESS.getErrorCode(), resultMsg, data);
    }

    public static JsonResult SUCCESS(Object data) {
        return SUCCESS(null, data);
    }


    public static JsonResult FAILURE(String resultMsg) {
        return new JsonResult(ErrorCode.FAILURE.getErrorCode(), resultMsg);
    }


    public static JsonResult RESULT(int resultCode, String resultMsg, String detailMsg) {
        return new JsonResult(resultCode, resultMsg, detailMsg);
    }

    public static JsonResult RESULT(int resultCode, String resultMsg, String detailMsg, Object data) {
        return new JsonResult(resultCode, resultMsg, detailMsg, data);
    }

    public static JsonResult ERROR(Exception e) {
        return new JsonResult(ErrorCode.ERROR.getErrorCode(), "服务器繁忙", e.getMessage());
    }


    public Object getResultCode() {
        return get("resultCode");
    }

    public void setResultCode(int resultCode) {
        put("resultCode", resultCode);
    }

    public String getResultMsg() {
        return getString("resultMsg");
    }

    public void setResultMsg(String resultMsg) {
        put("resultMsg", resultMsg);
    }

    public String getDetailMsg() {
        return getString("detailMsg");
    }

    public void setDetailMsg(String detailMsg) {
        put("detailMsg", detailMsg);
    }

    public Object getData() {
        return get("data");
    }

    public void setData(Object data) {
        put("data", data);
    }



}
