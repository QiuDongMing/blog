package com.codermi.sercurity.utils;

import com.alibaba.fastjson.JSON;
import com.codermi.common.base.utils.JsonResult;

import javax.servlet.http.HttpServletResponse;

/**
 * @author qiudm
 * @date 2018/9/19 17:52
 * @desc
 */
public class HttpUtils {

    public static void responseError(HttpServletResponse response, int resultCode, String resultMsg) throws Exception {
        JsonResult result = JsonResult.RESULT(resultCode, resultMsg, null);
        String text = JSON.toJSONString(result);
        response.setContentType("application/json; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(text);
        response.getWriter().flush();
    }

}
