package service.redis;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import service.BaseTest;

/**
 * @author qiudm
 * @date 2018/9/6 14:39
 * @desc
 */
public class RedisTest extends BaseTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void getTest() {
        String val = redisTemplate.opsForValue().get("a");
        System.out.println("val = " + val);

    }

}
