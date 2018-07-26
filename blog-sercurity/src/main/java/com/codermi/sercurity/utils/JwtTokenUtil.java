package com.codermi.sercurity.utils;
import com.alibaba.fastjson.JSON;
import com.codermi.blog.user.data.UserOpenInfo;
import com.codermi.common.base.utils.StringUtils;
import com.google.common.collect.Lists;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author qiudm
 * @date 2018/7/26 10:20
 * @desc
 */
public class JwtTokenUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);

    /**
     * 加密的私钥
     */
    private static final String SECRET_KEY = "mySecretKey";

    /**
     * 生成token默认 7天
     * @param userOpenInfo
     * @return
     */
    public static String generateToken(UserOpenInfo userOpenInfo) {
        return generateToken(userOpenInfo, TimeUnit.DAYS, 7L, null );
    }

    /**
     * 生成token默认 7天
     * @param userOpenInfo
     * @return
     */
    public static String generateToken(UserOpenInfo userOpenInfo, String subject) {
        return generateToken(userOpenInfo, TimeUnit.DAYS, 7L, subject);
    }


    /**
     * 生成token
     * @param userOpenInfo
     * @return
     */
    public static String generateToken(UserOpenInfo userOpenInfo, TimeUnit timeUnit, Long duration, String subject) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256; //指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
        Date now = new Date();
        String token = Jwts
                    .builder()
                    .setClaims(createClaims(userOpenInfo))
                    .setId(StringUtils.randomUUID())
                    .setSubject(subject)
                    .setIssuedAt(now)
                    .signWith(signatureAlgorithm, generalKey())
                    .setExpiration(new Date(now.getTime() + TimeUnit.MILLISECONDS.convert(duration, timeUnit)))
                    .compact();
        return token;
    }




    /**
     * 构建用户基础信息
     * @param userOpenInfo
     * @return
     */
    private static Claims createClaims(UserOpenInfo userOpenInfo) {
        Claims claims = Jwts.claims();
        claims.put("userInfo", JSON.toJSONString(userOpenInfo));
        return claims;
    }


    /**
     * 解密token
     * @param token
     * @return
     * @throws Exception
     */
    public static Claims parseToken(String token) throws Exception {
        SecretKey key = generalKey();               //签名秘钥，和生成的签名的秘钥一模一样
        Claims claims = Jwts.parser()               //得到DefaultJwtParser
                .setSigningKey(key)                 //设置签名的秘钥
                .parseClaimsJws(token).getBody();   //设置需要解析的jwt
        return claims;
    }


    /**
     * 从token中获取userOpenInfo
     * @param token
     * @return
     */
    public static UserOpenInfo getUserOpenInfo (String token) {
        UserOpenInfo userOpenInfo = null;
        try {
            String jsonString = String.valueOf(parseToken(token).get("userInfo"));
            userOpenInfo = JSON.parseObject(jsonString, UserOpenInfo.class) ;
        } catch (Exception e) {
            logger.error("failed get userOpenInfo from token, error detail:", e);
        }
        return userOpenInfo;
    }



    /**
     * 由字符串生成加密key
     * @return
     */
    private static SecretKey generalKey() {
        String stringKey = SECRET_KEY;
        byte[] encodedKey = Base64.decodeBase64(stringKey);
        //根据给定的字节数组使用AES加密算法构造一个密钥，使用 encodedKey中的始于且包含 0 到前 leng 个字节这是当然是所有。
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }


    public static void main(String[] args) throws Exception {
        UserOpenInfo userOpenInfo = new UserOpenInfo();
        userOpenInfo.setNickName("张三");
        userOpenInfo.setRoles(Lists.newArrayList("USER","ROLE_USER"));
        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
        String token = jwtTokenUtil.generateToken(userOpenInfo);
        System.out.println("token = " + token);
        UserOpenInfo userOpenInfo1 = jwtTokenUtil.getUserOpenInfo(token);
        System.out.println("userOpenInfo1.getNickName() = " + userOpenInfo1.getNickName());
        System.out.println("userOpenInfo1.headPic = " + userOpenInfo1.getHeadPic());
        System.out.println("userOpenInfo1 = " + JSON.toJSONString(userOpenInfo1));

    }





}
