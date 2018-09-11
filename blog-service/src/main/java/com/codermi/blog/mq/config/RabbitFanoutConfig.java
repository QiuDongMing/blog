package com.codermi.blog.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author qiudm
 * @date 2018/8/1 15:54
 * @desc
 */
@Configuration
public class RabbitFanoutConfig {

    //789
    public static final String QDM_EXCHANGE_FANOUT = "QDM_EXCHANGE_FANOUT";

//    @Bean
//    public Queue QDM_FANOUT() {
//        return  new Queue(QDM_EXCHANGE_FANOUT);
//    }

    @Bean
    public Queue queue1() {
        return  new Queue("queue1");
    }

    @Bean
    public Queue queue2() {
        return  new Queue("queue2");
    }

    @Bean
    public Queue queue3() {
        return  new Queue("queue3");
    }


    //创建Fanout交换器
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(QDM_EXCHANGE_FANOUT);
    }

    @Bean
    Binding bing() {
        return BindingBuilder.bind(queue1()).to(fanoutExchange());
    }

    @Bean
    Binding bing2() {
        return BindingBuilder.bind(queue2()).to(fanoutExchange());
    }

    @Bean
    Binding bing3() {
        return BindingBuilder.bind(queue3()).to(fanoutExchange());
    }


}
