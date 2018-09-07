package com.codermi.blog.mq.producer;

import com.codermi.blog.mq.config.RabbitFanoutConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author qiudm
 * @date 2018/8/1 15:28
 * @desc
 */
@Component
public class Sender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(String msg) {
        this.rabbitTemplate.convertAndSend(RabbitFanoutConfig.QDM_EXCHANGE_FANOUT, "", msg);
    }



}
