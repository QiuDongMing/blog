package service.user;

import com.alibaba.fastjson.JSON;
import com.codermi.blog.user.data.po.User;
import com.mongodb.WriteResult;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import service.BaseTest;

/**
 * @author qiudm
 * @date 2018/9/12 13:41
 * @desc
 */
public class MongoTest extends BaseTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void delUserTest() {
        Query query = Query.query(Criteria.where("userId").is("100006"));
        WriteResult remove = mongoTemplate.remove(query, User.class);
        System.out.println("JSON.toJSONString(remove) = " + JSON.toJSONString(remove));
    }



}
