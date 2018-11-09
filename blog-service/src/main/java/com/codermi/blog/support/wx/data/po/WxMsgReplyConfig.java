package com.codermi.blog.support.wx.data.po;

import com.codermi.blog.common.data.po.BaseInfo;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author qiudm
 * @date 2018/11/9 11:06
 * @desc
 */
@Document(collection = "t_wx_msg_reply_config")
@Data
public class WxMsgReplyConfig extends BaseInfo {

    /**
     * 请求内容
     */
    private String reqText;

    /**
     * 回复的内容
     */
    private String rspText;

    /**
     * 回复的消息类型
     * @see com.codermi.blog.support.wx.enums.WxEnums.MsgType
     */
    private String msgType;

    /**
     * 创建者id
     */
    private String userId;

}
