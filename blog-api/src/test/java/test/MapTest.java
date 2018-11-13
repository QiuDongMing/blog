package test;

import com.alibaba.fastjson.JSON;
import com.codermi.blog.user.cache.data.dto.UserInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import service.BaseTest;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qiudm
 * @date 2018/9/12 14:16
 * @desc
 */
public class MapTest extends BaseTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * javaBean 转 Map
     *
     * @param object 需要转换的javabean
     * @return 转换结果map
     */
    public Map<String, Object> beanToMap(Object object) throws Exception {
        Map<String, Object> map = new HashMap<>();

        Class cls = object.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(object));
        }
        return map;
    }


    /**
     * @param map 需要转换的map
     * @param cls 目标javaBean的类对象
     * @return 目标类object
     */
    public Object mapToBean(Map<String, Object> map, Class cls) throws Exception {
        Object object = cls.newInstance();
        for (String key : map.keySet()) {
            Field temFiels = cls.getDeclaredField(key);
            temFiels.setAccessible(true);
            temFiels.set(object, map.get(key));
        }
        return object;
    }



    @Test
    public void beanToMapTest() {
        UserInfo userInfo = new UserInfo();
      //  userInfo.setRoles(Lists.newArrayList("ROLE1","ROLE2","ROLE3"));
        userInfo.setEmail("qdm213@qq.com");
        userInfo.setBirthday(System.currentTimeMillis());
        userInfo.setHeadPic("headPic");
       // userInfo.setNickName("nickName");
        userInfo.setUserId("1001");
        Map<String, Object> map = Maps.newHashMap();
        try {
            map = beanToMap(userInfo);
            for (Map.Entry<String, Object> entry : map.entrySet() ) {
                System.out.println("key = " + entry.getKey() + ", val=" + entry.getValue());
            }
            redisTemplate.opsForHash().putAll("userId:1001", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void mapToBeanTest() {
        Map<Object, Object> entries = redisTemplate.opsForHash().entries("userId:1001");
        UserInfo userInfo = JSON.parseObject(JSON.toJSONString(entries), UserInfo.class);
        System.out.println("JSON.toJSONString(userInfo) = " + JSON.toJSONString(userInfo));
    }



}
