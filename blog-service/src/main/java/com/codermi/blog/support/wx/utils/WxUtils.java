package com.codermi.blog.support.wx.utils;

import com.codermi.common.base.utils.EncryUtils;
import com.codermi.common.base.utils.PropertiesUtil;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * @author qiudm
 * @date 2018/11/7 15:59
 * @desc
 */
public class WxUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(WxUtils.class);

    public static String getAppID() {
        return PropertiesUtil.getContextProperty("wx.blog.appID");
    }

    public static String getAppSecret() {
        return PropertiesUtil.getContextProperty("wx.blog.appsecret");
    }

    public static String getWxConfigToken() {
        return PropertiesUtil.getContextProperty("wx.blog.configToken");
    }



    /**
     * 校验微信的签名：
     * 1）将token、timestamp、nonce三个参数进行字典序排序
     * 2）将三个参数字符串拼接成一个字符串进行sha1加密
     * 3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public static boolean checkWxSignature(String signature, String timestamp, String nonce, String token) {
        List<String> sigList = Lists.newArrayList(token, timestamp, nonce);
        Collections.sort(sigList, Comparator.naturalOrder());
        String signSortContent = Joiner.on("").join(sigList);
        String signEncy = EncryUtils.string2Sha1(signSortContent);
        return Objects.equals(signEncy, signature);
    }


    /**
     * 解析微信的XML消息
     * @param request
     * @return
     */
    public static Map<String, String> parseWxXML(HttpServletRequest request) {
        Map<String, String> xmlRes = Maps.newHashMap();
        ServletInputStream inputStream = null;
        try {
            inputStream = request.getInputStream();
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(inputStream);
            Element rootElement = document.getRootElement();
            List<Element> elements = rootElement.elements();
            for (Element element : elements) {
                xmlRes.put(element.getName(), element.getText());
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (DocumentException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }
        return xmlRes;
    }














}
