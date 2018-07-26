package com.codermi.elasticjob;

import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author qiudm
 * @date 2018/7/17 15:47
 * @desc
 */
@Configuration
public class ElasticJobConfigure implements InitializingBean {

    @Bean
    public CoordinatorRegistryCenter regCenter(@Value("${elasticjob.zookeeper.server.lists}") final String serverList,
                                               @Value("${elasticjob.zookeeper.namespace}") final String namespace) {
        CoordinatorRegistryCenter coordinatorRegistryCenter = new ZookeeperRegistryCenter(
                new ZookeeperConfiguration(serverList, namespace));
        coordinatorRegistryCenter.init();
        return coordinatorRegistryCenter;
    }


    @Override
    public void afterPropertiesSet() throws Exception {

    }





}
