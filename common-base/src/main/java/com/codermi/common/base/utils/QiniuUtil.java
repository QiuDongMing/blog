package com.codermi.common.base.utils;

import com.codermi.common.base.constants.QiniuConstant;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;


/**
 * @author qiudm
 * @date 2018/6/28 18:11
 * @desc
 */
public class QiniuUtil {

    private static UploadManager uploadManager;
    private static Auth auth;
    private static Configuration cfg = new Configuration(Zone.zone0());
    private static final StringMap PUT_POLICY = new StringMap();
    private static  String upToken;

    private static Long tokenUpdateTime = 0L;

    static {
        if (uploadManager == null) {
            uploadManager = new UploadManager(cfg);
        }
        if (auth == null) {
            auth = Auth.create(PropertiesUtil.getContextProperty("qiniu.accessKey"),
                    PropertiesUtil.getContextProperty("qiniu.secretKey"));
        }
        PUT_POLICY.put("returnBody",
                "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)}");
    }


    public static String upload(byte[] bytes, String fileName) throws QiniuException {
        Response response = uploadManager.put(bytes, fileName, getUploadToken());
        return fileName;
    }

    public static String upload(byte[] bytes) throws QiniuException {
        String fileName = StringUtils.randomUUID();
        uploadManager.put(bytes, fileName, getUploadToken());
        return fileName;
    }

    public static String getUploadToken() {
        Long currentTime = System.currentTimeMillis();
        //缓存50秒
        if ((currentTime - tokenUpdateTime) > (50 * 1000)) {
            upToken = auth.uploadToken(QiniuConstant.DEFAULT_BUCKET, null, QiniuConstant.EXPIRE_SECONDS, PUT_POLICY);
            tokenUpdateTime = currentTime;
        }
        return upToken;
    }


}
