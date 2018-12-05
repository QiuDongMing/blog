package com.codermi.blog.controller.wx;

import com.codermi.blog.common.utils.ReqUtil;
import com.codermi.blog.support.wx.data.request.WxMsgReplyConfigRequest;
import com.codermi.blog.support.wx.service.IWxMsgReplyConfigService;
import com.codermi.blog.support.wx.service.IWxPubService;
import com.codermi.blog.user.data.request.group.AddGroup;
import com.codermi.common.base.utils.JsonResult;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

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

    @Autowired
    private IWxMsgReplyConfigService wxMsgReplyConfigService;

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


    @RequestMapping("/reply/add")
    @PostMapping("wx:reply:add")
    public JsonResult saveWxReplyConfig(@RequestBody @Validated(value = AddGroup.class)
                                                    WxMsgReplyConfigRequest request) {
        wxMsgReplyConfigService.saveWxReplyConfig(request, ReqUtil.instance.getUserId());
        return JsonResult.SUCCESS();
    }















}
