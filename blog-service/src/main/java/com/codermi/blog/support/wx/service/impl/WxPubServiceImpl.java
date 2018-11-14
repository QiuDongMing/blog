package com.codermi.blog.support.wx.service.impl;

import com.alibaba.fastjson.JSON;
import com.codermi.blog.common.constants.Constants.CacheKeyPre;
import com.codermi.blog.common.constants.URLConstants;
import com.codermi.blog.support.wx.constants.WxConstants;
import com.codermi.blog.support.wx.data.WxAccessToken;
import com.codermi.blog.support.wx.data.WxButton;
import com.codermi.blog.support.wx.data.WxMenu;
import com.codermi.blog.support.wx.data.WxUserInfo;
import com.codermi.blog.support.wx.data.po.WxMsgReplyConfig;
import com.codermi.blog.support.wx.enums.WxEnums.*;
import com.codermi.blog.support.wx.service.IWxMsgReplyConfigService;
import com.codermi.blog.support.wx.service.IWxPubService;
import com.codermi.blog.support.wx.utils.WxUtils;
import com.codermi.common.base.utils.HttpHelper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author qiudm
 * @date 2018/11/7 19:02
 * @desc
 */
@Service
public class WxPubServiceImpl implements IWxPubService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WxPubServiceImpl.class);


    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private IWxMsgReplyConfigService wxMsgReplyConfigService;

    /**
     * 获取统一认证的token
     * @return
     */
    private String getPubAccessToken() {
        String accessToken = this.getCacheWxPubAccessToken();
        if (StringUtils.isEmpty(accessToken)) {
            /**
             * 正常情况下，微信会返回下述JSON数据包给公众号：
             * {"access_token":"ACCESS_TOKEN","expires_in":7200}
             */
            String response = HttpHelper.get(MessageFormat
                    .format(URLConstants.GET_WX_PUB_ACCESS_TOKEN,
                            "client_credential", WxUtils.getAppID(), WxUtils.getAppSecret()), null);
            if (response != null && response.contains("access_token")) {
                WxAccessToken wxAccessToken = JSON.parseObject(response, WxAccessToken.class);
                this.cacheWxPubAccessToken(wxAccessToken);
                return wxAccessToken.getAccess_token();
            }
            LOGGER.error("获取微信统一认证token失败：AppId:{},response:{}", WxUtils.getAppID(), response);
        }
        return accessToken;
    }


    @Override
    public String handlerMsgEvent(HttpServletRequest request)  {

        //解析微信传入的XML
        Map<String, String> wxMsgMap = WxUtils.parseWxXML(request);

        for (Map.Entry<String, String> entry : wxMsgMap.entrySet()){
            LOGGER.info("key:{},val:{}", entry.getKey(), entry.getValue());
        }

        //消息类型
        MsgType msgType = MsgType.valueOf(wxMsgMap.get("MsgType"));
        //发送者帐号（open_id）
        String FromUserName = wxMsgMap.get("FromUserName");
        //公众号
        String ToUserName = wxMsgMap.get("ToUserName");

        String responseMsg = "";

        //接收的是文本类型的消息
        if (Objects.equals(msgType, MsgType.text)) {
            WxMsgReplyConfig wxMsgReplyConfig = wxMsgReplyConfigService.getByReqText(wxMsgMap.get("Content"));
            if (wxMsgReplyConfig == null) {
                wxMsgReplyConfig = wxMsgReplyConfigService.getByReqText(WxConstants.DEFAULT_REQ_TEXT);
            }
            String rspText = wxMsgReplyConfig == null ? WxConstants.DEFAULT_RSP_TEXT : wxMsgReplyConfig.getRspText();
            //处理文本消息
            responseMsg = sendTextMsg(ToUserName, FromUserName, rspText);
        } else if (Objects.equals(msgType, MsgType.event)) { //事件类型


            WxMsgReplyConfig wxMsgReplyConfig = wxMsgReplyConfigService.getByReqText(wxMsgMap.get("Content"));
            if (wxMsgReplyConfig == null) {
                wxMsgReplyConfig = wxMsgReplyConfigService.getByReqText(WxConstants.DEFAULT_REQ_TEXT);
            }
            String rspText = wxMsgReplyConfig == null ? WxConstants.DEFAULT_RSP_TEXT : wxMsgReplyConfig.getRspText();
            //处理文本消息
            responseMsg = sendTextMsg(ToUserName, FromUserName, rspText);
        }

        return responseMsg;
    }


    @Override
    public void addMenu(WxButton wxButton) {
        String response = HttpHelper.postJSON(MessageFormat
                .format(URLConstants.CREATE_WX_MENU, String.valueOf(getPubAccessToken())), wxButton);
        LOGGER.info("response:{}", response);
    }


    @Override
    public WxMenu getMenu() {
        String response = HttpHelper.get(MessageFormat
                .format(URLConstants.GET_WX_MENU, String.valueOf(getPubAccessToken())), null);
        return JSON.parseObject(response, WxMenu.class);
    }


    @Override
    public void delMenu() {


    }

    @Override
    public WxUserInfo getUserInfo(String openId) {
        String response = HttpHelper.get(MessageFormat
                .format(URLConstants.GET_WX_USER_INFO, getPubAccessToken(), openId, WxLang.zh_CN.name()), null);
        return JSON.parseObject(response, WxUserInfo.class);
    }

    /**
     * 发送文本消息内容
     * @param FromUserName  发送消息者
     * @param ToUserName    消息接收者openId
     * @param content       发送的内容
     * @return
     */
   private String sendTextMsg(String FromUserName, String ToUserName, String content) {
       return  String.format(
               "<xml>" +
                       "<ToUserName><![CDATA[%s]]></ToUserName>" +
                       "<FromUserName><![CDATA[%s]]></FromUserName>" +
                       "<CreateTime>%s</CreateTime>" +
                       "<MsgType><![CDATA[text]]></MsgType>" +
                       "<Content><![CDATA[%s]]></Content>" +
                       "</xml>",
               ToUserName, FromUserName , System.currentTimeMillis() / 1000, content);
   }












    /**
     * 缓存全局微信token
     *
     * @param wxAccessToken
     */
    private void cacheWxPubAccessToken(WxAccessToken wxAccessToken) {
        if (Objects.isNull(wxAccessToken)) {
            return;
        }
        redisTemplate
                .opsForValue()
                .set(
                        CacheKeyPre.WX_PUB_ACCESS_TOKEN,
                        wxAccessToken.getAccess_token(),
                        wxAccessToken.getExpires_in(),
                        TimeUnit.SECONDS
                );
    }

    /**
     * 获取缓存中的全局微信token
     *
     * @return
     */
    private String getCacheWxPubAccessToken() {
        return redisTemplate.opsForValue().get(CacheKeyPre.WX_PUB_ACCESS_TOKEN);
    }


}
