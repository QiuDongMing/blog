package com.codermi.blog.controller.wx;

import com.alibaba.fastjson.JSON;
import com.codermi.blog.support.wx.service.IWxPubService;
import io.swagger.annotations.Api;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author qiudm
 * @date 2018/11/7 16:01
 * @desc
 */
@Api(value = "微信相关")
@RequestMapping("/wx")
@RestController
public class WxController extends AbstractWxController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WxController.class);

    @Autowired
    private IWxPubService wxPubService;


    /**
     * URL是开发者用来接收微信消息和事件的接口URL
     * GET方式验证消息来源是微信服务器
     * @param request
     */
    @GetMapping("/open/handle")
    public void handleGetWx(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("GET方式验证消息来源是微信服务器");
        checkWxRequest(request, response);
    }


    /**
     * URL是开发者用来接收微信消息和事件的接口URL
     * 当普通微信用户向公众账号发消息时，微信服务器将POST消息的XML数据包到开发者填写的URL上。
     * @param request
     */
    @PostMapping("/open/handle")
    public void handlePostWx(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("post handle wx message and event");
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e.getMessage(), e);
        }
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = null;
        try {
            String responseMsg = wxPubService.handlerMsgEvent(request);
            out = response.getWriter();
            out.print(responseMsg);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (out != null) {
                out.close();
            }
        }

    }






}
