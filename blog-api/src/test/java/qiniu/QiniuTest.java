package qiniu;

import com.codermi.common.base.utils.QiniuUtil;
import org.junit.Test;
import service.BaseTest;

/**
 * @author qiudm
 * @date 2018/9/28 17:58
 * @desc
 */

public class QiniuTest extends BaseTest {

    @Test
    public void getToken() {
        String uploadToken = QiniuUtil.getUploadToken();
        System.out.println("uploadToken = " + uploadToken);
    }





}
