package com.xhu.xyjy.websocket;

import com.xhu.xyjy.pojo.Message;
import com.xhu.xyjy.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 * @ServerEndpoint 可以把当前类变成websocket服务类
 */




@ServerEndpoint("/groupwebsocket/{userId}")
@Component
public class GroupWebSocket {
    static ChatService chatService;

    @Autowired
    public void setRabbitAdmin(ChatService chatService){
        GroupWebSocket.chatService = chatService;
    }

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户id
     public static ConcurrentHashMap<String, GroupWebSocket> groupWebSocket = new ConcurrentHashMap<String, GroupWebSocket>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //当前发消息的人员编号
    private String userId = "";
    private String userName = "";


    /**
     * 线程安全的统计在线人数
     *
     * @return
     */
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        GroupWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        GroupWebSocket.onlineCount--;
    }

    /**
     * 连接建立成功调用的方法
     *
     * @param param   用户唯一标示
     * @param session 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(@PathParam(value = "userId") String param, Session session) {
        userId = param;//接收到发送消息的人员编号
        this.session = session;

        if(!groupWebSocket.containsKey(param)){
            groupWebSocket.put(param, this);//加入线程安全map中
            addOnlineCount();           //在线数加1
            System.out.println("用户id：" + param + "加入连接！当前在线人数为" + getOnlineCount());
        }
        sendAll("用户"+param+"加入群聊");

        for (String value : groupWebSocket.keySet()) {

            System.out.println("Value = " + value);

        }

    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if (!userId.equals("")) {
            groupWebSocket.remove(userId);  //根据用户id从ma中删除
            subOnlineCount();           //在线数减1
            System.out.println("用户id：" + userId + "关闭连接！当前在线人数为" + getOnlineCount());
            sendAll(userId + "已推出群聊" );
    }
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);

        //发送人姓名
        userName = message.split(",")[0];
        //发送的信息
        String sendMessage = message.split(",")[1];
        //消息类型
        String type=message.split(",")[2];


        if(Integer.parseInt(type)==1){

            sendAll(userName+":"+sendMessage);
        }






    }

    /**
     * 给指定的人发送消息
     *
     * @param message
     */
    public void sendToUser(String sendUserId, String message) {

        try {
            if (groupWebSocket.get(sendUserId) != null) {
                groupWebSocket.get(sendUserId).sendMessage(userId + "给我发来消息，消息内容为--->>" + message);
            } else {
                //如果发送人在线，则向发送人发送反馈消息
                if (groupWebSocket.get(userId) != null) {
                    groupWebSocket.get(userId).sendMessage("用户id：" + sendUserId + "以离线，未收到您的信息！");
                }
                chatService.addUnread(Integer.parseInt(userId),Integer.parseInt(sendUserId));
                chatService.updateTime(Integer.parseInt(userId),Integer.parseInt(sendUserId));

                System.out.println("消息接受人:" + sendUserId + "已经离线！");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 管理员发送消息
     *
     * @param message
     */
    public void systemSendToUser(String sendUserId, String message) {

        try {
            if (groupWebSocket.get(sendUserId) != null) {
                groupWebSocket.get(sendUserId).sendMessage("系统给我发来消息，消息内容为--->>" + message);
            } else {
                System.out.println("消息接受人:" + sendUserId + "已经离线！");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 给所有人发消息
     *
     * @param message
     */
    private void sendAll(String message) {

        //遍历HashMap
        for (String key : groupWebSocket.keySet()) {
            try {
                //判断接收用户是否是当前发消息的用户
                if (!userId.equals(key)) {
                    groupWebSocket.get(key).sendMessage( message);
                    System.out.println("key = " + key);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 发生错误时调用
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    /**
     * 发送消息
     *
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        //同步发送
        this.session.getBasicRemote().sendText(message);
        //异步发送
        //this.session.getAsyncRemote().sendText(message);
    }
}
