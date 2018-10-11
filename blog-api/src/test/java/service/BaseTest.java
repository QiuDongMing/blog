package service;

import com.codermi.blog.BlogApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author qiudm
 * @date 2018/6/28 11:40
 * @desc
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = BlogApp.class)
public class BaseTest {

    @Test
    public void myTest() {

    }

}
