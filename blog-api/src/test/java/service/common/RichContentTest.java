package service.common;

import com.coderme.blog.common.dao.IRichContentDao;
import com.coderme.blog.common.data.po.RichContent;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import service.BaseTest;

/**
 * @author qiudm
 * @date 2018/6/29 10:00
 * @desc
 */
public class RichContentTest extends BaseTest {

    @Autowired
    private IRichContentDao richContentDao;

    @Test
    public void insertGetIdTest() {
        RichContent richContent = new RichContent();
        richContent.setContent("test");
        richContentDao.saveRichContent(richContent);
    }



}
