package top.zhangdashuai.websocket.server;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author zhangdashuai
 */
@Service
@ServerEndpoint("/websocket")
public class WebSocketServer {

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("连接成功:" + session.getId());
        try {
            session.getBasicRemote().sendText("连接成功");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("连接关闭:" + session.getId());
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("收到消息:" + message);
    }

    @OnError
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }
}
