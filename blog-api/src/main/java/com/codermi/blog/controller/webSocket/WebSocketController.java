package com.codermi.blog.controller.webSocket;

import com.alibaba.fastjson.JSON;
import com.codermi.blog.user.data.po.User;
import com.codermi.blog.websocket.config.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


/**
 * @author qiudm
 * @date 2018/6/29 14:30
 * @desc
 */
@RequestMapping("websocket")
@RestController
public class WebSocketController {

    @Autowired
    private WebSocket myWebSocket;

    @GetMapping("/send/{userId}")
    public void sendMessage(String message, @PathVariable("userId") String userId) {


        System.out.println("message:" + (message));
        for (int i = 0; i < 100000; i++) {
            User user = new User();
            user.setNickName("张三" + i);
            user.setCreateTime(System.currentTimeMillis());
            user.setUserId("1001");
            user.setBirthday(System.currentTimeMillis());

            message = JSON.toJSONString(user);
            try {
                myWebSocket.sendMessage(userId, message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @GetMapping("/sendall")
    public void sendAll(String message) {
        System.out.println("message:" + (message));
        myWebSocket.sendMessageToAll(message);
    }

}
