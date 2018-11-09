package service.support.wx;

import com.alibaba.fastjson.JSON;
import com.codermi.blog.common.constants.Constants;
import com.codermi.blog.support.wx.constants.WxConstants;
import com.codermi.blog.support.wx.dao.IWxMsgReplyConfigDao;
import com.codermi.blog.support.wx.data.Button;
import com.codermi.blog.support.wx.data.WxButton;
import com.codermi.blog.support.wx.data.WxMenu;
import com.codermi.blog.support.wx.data.WxUserInfo;
import com.codermi.blog.support.wx.data.request.WxMsgReplyConfigRequest;
import com.codermi.blog.support.wx.enums.WxEnums;
import com.codermi.blog.support.wx.service.IWxMsgReplyConfigService;
import com.codermi.blog.support.wx.service.IWxPubService;
import com.codermi.blog.support.wx.utils.WxUtils;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import service.BaseTest;

import java.util.List;

/**
 * @author qiudm
 * @date 2018/11/8 15:01
 * @desc
 */
public class WxTests extends BaseTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(WxUtils.class);

    @Autowired
    private IWxPubService wxPubService;
    @Autowired
    private IWxMsgReplyConfigService wxMsgReplyConfigService;

//    @Test
    public void getPubAccessToken() {

    }

   // @Test
    public void saveWxMsgReplyConfig() {

       // for(int i=1; i<5; i++) {
            WxMsgReplyConfigRequest replyConfigRequest = new WxMsgReplyConfigRequest();
            replyConfigRequest.setMsgType("text");
            replyConfigRequest.setRspText(WxConstants.DEFAULT_RSP_TEXT);
            replyConfigRequest.setReqText(WxConstants.DEFAULT_REQ_TEXT);
            wxMsgReplyConfigService.saveWxReplyConfig(replyConfigRequest, "1001");
        //}

    }

   // @Test
    public void addWxMenu() {
        WxButton wxButton = new WxButton();
        Button button1_1 = new Button();
        button1_1.setName("按钮1");
        button1_1.setType(WxEnums.ButtonType.click.name());
        button1_1.setKey("btn_1_1");

        Button button1_2 = new Button();
        button1_2.setName("按钮2");

        Button button1_3 = new Button();
        button1_3.setName("按钮3");
        button1_3.setType(WxEnums.ButtonType.view.name());
        button1_3.setUrl("http://codermi.cn");

        Button button2 = new Button();
        button2.setName("二级-1");
        button2.setKey("key2-1");
        button2.setType(WxEnums.ButtonType.click.name());

        button1_2.setSub_button(Lists.newArrayList(button2));

        List<Button> buttons = Lists.newArrayList(button1_1, button1_2, button1_3);
        wxButton.setButton(buttons);
        wxPubService.addMenu(wxButton);
    }


    @Test
    public void getWxMenu() {
        WxMenu menu = wxPubService.getMenu();
        LOGGER.info("menu:{}", JSON.toJSONString(menu));
    }

    @Test
    public void getWxUserInfo() {
        String openId = "ogZGv54yF8JYFJo9eyewiIUix5TI";
        WxUserInfo userInfo = wxPubService.getUserInfo(openId);
        LOGGER.info("userInfo:{}", JSON.toJSONString(userInfo));
    }













}


