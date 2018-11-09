package com.codermi.blog.controller.wx;

import com.codermi.blog.support.wx.utils.WxUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author qiudm
 * @date 2018/11/7 16:24
 * @desc
 */
public abstract class AbstractWxController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractWxController.class);

    protected void checkWxRequest(HttpServletRequest request, HttpServletResponse response) {
        //微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
        String signature = request.getParameter("signature");
        //时间戳
        String timestamp = request.getParameter("timestamp");
        //随机数
        String nonce = request.getParameter("nonce");
        //随机字符串
        String echostr = request.getParameter("echostr");
        LOGGER.info("signature:{}, timestamp:{}, nonce:{}, echostr:{}", signature, timestamp, nonce, echostr);
        PrintWriter out;
        try {
            out = response.getWriter();
            //校验是否来自微信
            boolean wxSignature = WxUtils.checkWxSignature(signature, timestamp, nonce, WxUtils.getWxConfigToken());
            if (wxSignature) {
                out.print(echostr);
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }















}
