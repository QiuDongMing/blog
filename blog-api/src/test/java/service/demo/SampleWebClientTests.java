package service.demo;

import com.codermi.blog.BlogApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.*;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertThat;



/**
 * @author qiudm
 * @date 2018/10/11 15:02
 * @desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = BlogApp.class)
public class SampleWebClientTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(SampleWebClientTests.class);

    @Autowired
    private TestRestTemplate template;

    @Test
    public void testRequest() {
        String url = "http://blog.codermi.cn";
        url = "http://192.168.100.3:8585/user/open/test";
        ResponseEntity<String> forEntity = template.getForEntity(url, String.class);
        String body = this.template.getForEntity( url, String.class).getBody();



        HttpHeaders headers = this.template.getForEntity(
                "http://blog.codermi.cn", String.class).getHeaders();

        LOGGER.info("hearder:{}", headers);
        LOGGER.info("body:{}", body);
        LOGGER.info("headers.getLocation:{}",headers.getLocation());


    }


    @TestConfiguration
    static class Config {
        @Bean
        public RestTemplateBuilder restTemplateBuilder() {
            return new RestTemplateBuilder().setConnectTimeout(1000).setReadTimeout(1000);
        }
    }






}
