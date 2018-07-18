package com.coderme.blog.article.task;

import com.alibaba.fastjson.JSON;
import com.coderme.elasticjob.ElasticJobConf;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author qiudm
 * @date 2018/7/16 15:12
 * @desc
 */
@ElasticJobConf(
        name = "test1",
        cron = "0/10 * * * * ?"
)
public class ElasticJobTest implements SimpleJob {

    private static final Logger logger = LoggerFactory.getLogger(ElasticJobTest.class);

    @Override
    public void execute(ShardingContext shardingContext) {
        logger.info("elastic-job test");
        logger.info("shardingContext:{}", JSON.toJSONString(shardingContext));
    }


}
