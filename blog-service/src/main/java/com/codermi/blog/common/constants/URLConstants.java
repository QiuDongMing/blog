package com.codermi.blog.common.constants;

/**
 * @author qiudm
 * @date 2018/11/7 19:14
 * @desc
 */
public interface URLConstants {


    /**
     * 获取全局的唯一接口调用凭据
     * access_token是公众号的全局唯一接口调用凭据，公众号调用各接口时都需使用access_token
     * grant_type： 是      获取access_token填写client_credential
     * appid：	    是	   第三方用户唯一凭证
     * secret：	    是	   第三方用户唯一凭证密钥，即appsecret
     */
    String GET_WX_PUB_ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type={0}&appid={1}&secret={2}";


    /**
     * 微信创建菜单
     * POST JSON
     */
    String CREATE_WX_MENU =  "https://api.weixin.qq.com/cgi-bin/menu/create?access_token={0}";




}
