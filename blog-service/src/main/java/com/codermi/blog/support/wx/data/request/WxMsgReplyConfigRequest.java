package com.codermi.blog.support.wx.data.request;

import com.codermi.blog.user.data.request.group.AddGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author qiudm
 * @date 2018/11/9 11:32
 * @desc
 */
@Data
public class WxMsgReplyConfigRequest {

    @ApiModelProperty(value = "公众号发送者发送的文本")
    private String reqText;

    @ApiModelProperty(value = "给公众号发送者发送的内容类型：text:文本 ")
    @NotBlank(message = "发送的消息类型不能为空", groups = AddGroup.class)
    private String msgType;

    @ApiModelProperty(value = "反馈给公众号发送者的文本")
    private String rspText;

}
