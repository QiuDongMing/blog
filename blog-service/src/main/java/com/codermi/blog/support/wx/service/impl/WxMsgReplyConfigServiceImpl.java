package com.codermi.blog.support.wx.service.impl;

import com.codermi.blog.exception.ServiceException;
import com.codermi.blog.support.wx.dao.IWxMsgReplyConfigDao;
import com.codermi.blog.support.wx.data.po.WxMsgReplyConfig;
import com.codermi.blog.support.wx.data.request.WxMsgReplyConfigRequest;
import com.codermi.blog.support.wx.enums.WxEnums.*;
import com.codermi.blog.support.wx.service.IWxMsgReplyConfigService;
import com.codermi.common.base.utils.BeanUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author qiudm
 * @date 2018/11/9 11:41
 * @desc
 */
@Service
public class WxMsgReplyConfigServiceImpl implements IWxMsgReplyConfigService {

    @Autowired
    private IWxMsgReplyConfigDao wxMsgReplyConfigDao;

    @Override
    public void saveWxReplyConfig(WxMsgReplyConfigRequest request, String userId) {
        validateRequest(request);
        WxMsgReplyConfig wxMsgReplyConfig = BeanUtil.copy(request, WxMsgReplyConfig.class);
        wxMsgReplyConfig.setUserId(userId);
        long currentTimeMillis = System.currentTimeMillis();
        wxMsgReplyConfig.setUpdateTime(currentTimeMillis);
        wxMsgReplyConfig.setCreateTime(currentTimeMillis);
        wxMsgReplyConfigDao.saveWxMsgReplyConfig(wxMsgReplyConfig);
    }


    @Override
    public WxMsgReplyConfig getByReqText(String reqText) {
        return wxMsgReplyConfigDao.getByReqText(reqText);
    }



    /**
     * 校验请求参数
     * @param request
     */
    private void validateRequest(WxMsgReplyConfigRequest request) {
        if (StringUtils.isBlank(request.getMsgType())) {
            throw new ServiceException("要发送的消息类型不能为空");
        }
        MsgType msgType = MsgType.valueOf(request.getMsgType());
        //文本消息
        if (Objects.equals(msgType, MsgType.text)) {
            if (StringUtils.isBlank(request.getReqText())) {
                throw new ServiceException("公众号发送者发送的文本不能为空");
            }

            if (StringUtils.isBlank(request.getRspText())) {
                throw new ServiceException("反馈给公众号发送者的文本不能为空");
            }

            WxMsgReplyConfig wxMsgReplyConfig = wxMsgReplyConfigDao
                    .getByReqText(request.getReqText().trim());

            if (Objects.nonNull(wxMsgReplyConfig)) {
                throw new ServiceException("该发送文本已经存在");
            }

        } else {
            throw new ServiceException("不支持的消息类型");
        }
    }














}
