package com.coderme.blog.websocket.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author qiudm
 * @date 2018/6/29 13:56
 * @desc
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 */
@ServerEndpoint("/websocket/{userId}")
@Component
public class WebSocket {

    private static final Logger logger = LoggerFactory.getLogger(WebSocket.class);

    /**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
     */
    private static AtomicLong onLineCount = new AtomicLong(0);

    /**
     * 线程安全的map存储当前连接的webSocket客户端
     * Map: userId:webSocket
     */
    private static Map<String, WebSocket> webSocketMap = new ConcurrentHashMap<>();

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    /**
     * 连接的客户端的用户id
     */
    private String userId;

    /**
     * 连接建立成功调用的方法
     *
     * @param session 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.session = session;
        this.userId = userId;

        webSocketMap.put(userId, this);
        Long count = addOnlineCount();//在线数加1
        logger.info("a new client join, current online people count:{}", count);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketMap.remove(this.userId);   //从map中删除
        Long count = subOnlineCount();      //在线数减1
        logger.info("a client closed, current online people count:{}", count);
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("message from client:{}", message);
    }

    /**
     * 发生错误时调用
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        logger.error("unExpect error occured, error message:");
        error.printStackTrace();
    }

    /**
     * 群发消息
     *
     * @param message
     */
    public void sendMessageToAll(String message) {
        webSocketMap.forEach((wk, wv) -> {
            try {
                wv.sendMessage(message);
            } catch (IOException e) {
                logger.error("send message to:{} failed, send Message is:{}", wk, message);
                e.printStackTrace();
            }
        });
    }

    /**
     * 发送消息
     *
     * @param userId
     * @param message
     */
    public void sendMessage(String userId, String message) throws IOException {
        WebSocket webSocket = webSocketMap.get(userId);
        if (webSocket != null) {
            webSocket.sendMessage(message);
        }
    }

    /**
     * 给当前连接session发送消息
     *
     * @param message
     * @throws IOException
     */
    private void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public static Long addOnlineCount() {
        return WebSocket.onLineCount.incrementAndGet();
    }

    public static Long subOnlineCount() {
        return WebSocket.onLineCount.decrementAndGet();
    }

}
