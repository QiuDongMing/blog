package service.support.wx;

import com.codermi.blog.common.constants.Constants;
import com.codermi.blog.support.wx.constants.WxConstants;
import com.codermi.blog.support.wx.dao.IWxMsgReplyConfigDao;
import com.codermi.blog.support.wx.data.request.WxMsgReplyConfigRequest;
import com.codermi.blog.support.wx.service.IWxMsgReplyConfigService;
import com.codermi.blog.support.wx.service.IWxPubService;
import com.codermi.blog.support.wx.utils.WxUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import service.BaseTest;

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

    @Test
    public void saveWxMsgReplyConfig() {

       // for(int i=1; i<5; i++) {
            WxMsgReplyConfigRequest replyConfigRequest = new WxMsgReplyConfigRequest();
            replyConfigRequest.setMsgType("text");
            replyConfigRequest.setRspText(WxConstants.DEFAULT_RSP_TEXT);
            replyConfigRequest.setReqText(WxConstants.DEFAULT_REQ_TEXT);
            wxMsgReplyConfigService.saveWxReplyConfig(replyConfigRequest, "1001");
        //}

    }
















}


