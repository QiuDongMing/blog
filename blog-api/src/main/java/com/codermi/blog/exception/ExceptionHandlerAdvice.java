package com.codermi.blog.exception;

import com.alibaba.fastjson.JSON;
import com.codermi.common.base.enums.ErrorCode;
import com.codermi.common.base.utils.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author qiudm
 * @date 2018/7/27 3:04
 * @desc
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public void handleErrors(HttpServletRequest request, HttpServletResponse response, Exception e)
            throws Exception {
        logger.error("接口请求错误：{}", request.getRequestURI());
        Map<String, String[]> paramMap = request.getParameterMap();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
            sb.append(entry.getKey()).append("=").append(paramMap.get(entry.getKey())[0])
                    .append("&");
        }
        logger.error("接口参数: {}", sb.toString());
        logger.error("错误内容：{}", e.getMessage());
        e.printStackTrace();

        int resultCode = ErrorCode.ERROR.getErrorCode();
        String resultMsg = "服务器繁忙，请稍后再试！";
        String detailMsg = "";

        if (e != null) {
            if (e instanceof MissingServletRequestParameterException || e instanceof BindException) {
                resultCode = ErrorCode.MISS_PARAM.getErrorCode();
                resultMsg = "请求参数验证失败，缺少必填参数或参数错误";
            } else if (e instanceof MethodArgumentNotValidException) {
                resultCode = ErrorCode.MISS_PARAM.getErrorCode();
                List<ObjectError> allErrors = ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors();
                resultMsg = allErrors.get(0).getDefaultMessage();
            } else if(e instanceof AccessDeniedException) {
                resultCode = ErrorCode.FORBIDEN_ACCESS.getErrorCode();
                resultMsg = e.getMessage();
            } else if (e instanceof ServiceException) {
                ServiceException ex = ((ServiceException) e);
                resultCode = null == ex.getResultCode() ? 0 : ex.getResultCode();
                resultMsg = ex.getMessage();
            } else {
                detailMsg = e.getMessage();
            }
        }

        JsonResult result = JsonResult.RESULT(resultCode, resultMsg, detailMsg);

        String text = JSON.toJSONString(result);

        response.setContentType("application/json; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(text);
        response.getWriter().flush();
    }


}
