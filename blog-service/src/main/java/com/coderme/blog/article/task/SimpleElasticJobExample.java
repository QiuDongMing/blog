package com.coderme.blog.article.task;

import com.alibaba.fastjson.JSON;
import com.coderme.elasticjob.ElasticJobConf;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author qiudm
 * @date 2018/7/16 15:12
 * @desc
 */
@ElasticJobConf(
        name = "blog_example",
        cron = "0 0/5 * * * ? ",
        jobExceptionHandler = "com.coderme.elasticjob.handler.JobExceptionHandler",
        description = "分布式作业调度测试"

)
public class SimpleElasticJobExample implements SimpleJob {

    private static final Logger logger = LoggerFactory.getLogger(SimpleElasticJobExample.class);

    @Override
    public void execute(ShardingContext shardingContext) {
        logger.info("elastic-job test");
//        int x = 1 / 0;
        logger.info("shardingContext:{}", JSON.toJSONString(shardingContext));
    }

}
