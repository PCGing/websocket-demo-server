package com.pcg.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ServerEndpoint("/websocket/{sid}")
public class WebSocketServer {

    /**
     * 静态变量，用来记录当前在线连接数。
    **/
    private static Map<String,Integer> onlineCount = new HashMap <> (  );

    private static final Map <String, Set <Session> > rooms = new ConcurrentHashMap ();

    //接收sid
    private String sid="";
    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session,@PathParam("sid") String sid) {

        if (!rooms.containsKey(sid)) {
            onlineCount.put ( sid,1 );
            // 创建房间不存在时，创建房间
            Set<Session> room = new HashSet <> ();
            // 添加用户
            room.add(session);
            rooms.put(sid, room);
        } else {
            // 房间已存在，直接添加用户到相应的房间
            onlineCount.put ( sid, onlineCount.get(sid)+1);
            rooms.get(sid).add(session);
        }
        log.info("有新窗口开始监听:"+sid+",当前在线人数为" + onlineCount.get ( sid ));
        this.sid=sid;
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        if(!session.isOpen()){
            rooms.get(sid).remove(session);
            onlineCount.put ( sid, onlineCount.get(sid)-1);
            log.info("有一连接关闭！当前" + sid + "在线人数为" + onlineCount.get ( sid ));
        }
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage( String message, Session session) throws Exception {
        log.info("收到来自窗口"+sid+"的信息:"+message);
        sendMessage(message);
        //群发消息
//        for (WebSocketServer item : webSocketSet) {
//            try {
//                item.sendMessage(message);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }
    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        for (Session session : rooms.get(sid)) {
            session.getBasicRemote().sendText(message);
        }
    }

    /**
     * 群发自定义消息
     * */
    public static void sendInfo(String message, String sid) throws IOException {
        log.info("推送消息到窗口"+sid+"，推送内容:"+message);
        for (Session session : rooms.get(sid)) {
            session.getBasicRemote().sendText(message);
        }
    }
}