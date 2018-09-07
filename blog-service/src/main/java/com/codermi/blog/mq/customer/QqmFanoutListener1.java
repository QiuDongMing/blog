package com.codermi.blog.mq.customer;


import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author qiudm
 * @date 2018/8/1 16:05
 * @desc
 */
@Component
@RabbitListener(queues = "queue1")
public class QqmFanoutListener1 {

    @RabbitHandler
    public void process(String message) {
        System.out.println("fanout Receiver1:" + message);
    }

}
