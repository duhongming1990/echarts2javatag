package com.hrhx.socket;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArraySet;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
  
public class PIWebAPIWebSocketBF implements WebSocketHandler{
	
    //private static final ArrayList<WebSocketSession> users = new ArrayList<WebSocketSession>();//这个会出现性能问题，最好用Map来存储，key用userid 
    private static CopyOnWriteArraySet<WebSocketSession> users = new CopyOnWriteArraySet<WebSocketSession>();
    /**
     * 连接成功时候，会触发页面上onopen方法
     */
    @Override  
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {   
        users.add(session);  
        System.out.println("afterConnectionEstablished");
//        Object obj = JedisUtils.getObject("bfList");
//        session.sendMessage(new TextMessage(JsonMapper.toJsonString(obj))); 
    }  
    /**
     * js调用websocket.send时候，会调用该方法
     */
    @Override  
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {  
        System.out.println("handleMessage" + message.toString());   
        //this.sendMessageToUsers(); 
        session.sendMessage(new TextMessage(new Date() + ""));  
    }  
   
    @Override  
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {  
        if(session.isOpen()){  
            //session.close();  
        } 
        System.out.println("handleTransportError");
        users.remove(session);  
    }  
    /**
     * 关闭连接时触发
     */
    @Override  
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus){
    	System.out.println("WebSocketSession");
        users.remove(session);     
    }  
   
    @Override  
    public boolean supportsPartialMessages() {  
        return false;  
    }  
    
    /**
     * 给某个用户发送消息
     *
     * @param userName
     * @param message
     */
    public void sendMessageToUser(String userName, TextMessage message) {
        for (WebSocketSession user : users) {
            if (user.getAttributes().get("WEBSOCKET_USERNAME").equals(userName)) {
                try {
                    if (user.isOpen()) {
                        user.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
    
    /**
     * 给所有在线用户发送消息
     *
     * @param message
     */
    public void sendMessageToUsers(TextMessage message) {
        for (WebSocketSession user : users) {
            try {
                if (user.isOpen()) {
                    user.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
