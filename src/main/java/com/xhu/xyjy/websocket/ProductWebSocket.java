package com.xhu.xyjy.websocket;

import com.xhu.xyjy.dao.ChatMapper;
import com.xhu.xyjy.dao.UserMapper;
import com.xhu.xyjy.dto.ResultData;
import com.xhu.xyjy.pojo.Message;
import com.xhu.xyjy.pojo.User;
import com.xhu.xyjy.service.ChatService;
import com.xhu.xyjy.service.ChatServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.concurrent.ConcurrentHashMap;

import static com.xhu.xyjy.utils.uploadUtil.upload;

/**
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 * @ServerEndpoint 可以把当前类变成websocket服务类
 */




@ServerEndpoint("/websocket/{userId}")
@Component
public class ProductWebSocket {
    static ChatService chatService;
    @Autowired
    public void setRabbitAdmin(ChatService chatService){
        ProductWebSocket.chatService = chatService;
    }

    static UserMapper userMapper;
    @Autowired
    public void setRabbitAdmin2(UserMapper userMapper){
        ProductWebSocket.userMapper=userMapper;
    }

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户id
     private static ConcurrentHashMap<String, ProductWebSocket> webSocketSet = new ConcurrentHashMap<String, ProductWebSocket>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //当前发消息的人员编号
    private String userId = "";


    /**
     * 线程安全的统计在线人数
     *
     * @return
     */
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        ProductWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        ProductWebSocket.onlineCount--;
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
        if(!webSocketSet.containsKey(param)){
            webSocketSet.put(param, this);//加入线程安全map中
            addOnlineCount();           //在线数加1
            System.out.println("用户id：" + param + "加入连接！当前在线人数为" + getOnlineCount());
        }

        for (String value : webSocketSet.keySet()) {

            System.out.println("Value = " + value);

        }

    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if (!userId.equals("")) {
            webSocketSet.remove(userId);  //根据用户id从ma中删除
            subOnlineCount();           //在线数减1
            System.out.println("用户id：" + userId + "关闭连接！当前在线人数为" + getOnlineCount());
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

        //要发送人的用户uid
        String sendUserId = message.split(",")[0];
        //发送的信息
        String sendMessage = message.split(",")[1];
        //消息类型
        String type=message.split(",")[2];
        if(Integer.parseInt(type)==1){
            Message message1=new Message();
            //数据填充
            message1.setUser_id(Integer.parseInt(userId));
            message1.setUser2_id(Integer.parseInt(sendUserId));
            message1.setContent(sendMessage);
            message1.setTime(new Timestamp(System.currentTimeMillis()));
            message1.setType(Integer.parseInt(type));
            //将消息存入数据库
            chatService.addMessage( message1);

            User user=userMapper.findById(Integer.parseInt(userId));
            //给指定的人发消息
            sendMessage=user.getUser_picture()+"["+sendMessage;
            sendToUser(sendUserId, sendMessage);
            System.out.println("发送消息了");
        }

        if(Integer.parseInt(type)==3){

            sendAll(message);
        }
        if(Integer.parseInt(type)==4){

            User user=userMapper.findById(Integer.parseInt(userId));
            sendToUser(sendUserId, user.getUser_name()+':'+sendMessage);
            System.out.println(user.getUser_name()+':'+sendMessage);
        }






    }

    /**
     * 给指定的人发送消息
     *
     * @param message
     */
    public void sendToUser(String sendUserId, String message) {

        try {
            if (webSocketSet.get(sendUserId) != null) {
                webSocketSet.get(sendUserId).sendMessage(message);
            } else {
                //如果发送人在线，则向发送人发送反馈消息
                if (webSocketSet.get(userId) != null) {
                    User user=userMapper.findById(Integer.parseInt(sendUserId));
                    webSocketSet.get(userId).sendMessage(user.getUser_picture()+"[我暂时不在线、未能收到您的信息！");
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
            if (webSocketSet.get(sendUserId) != null) {
                webSocketSet.get(sendUserId).sendMessage("系统给我发来消息，消息内容为--->>" + message);
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
        String sendMessage = message.split(",")[0];
        //遍历HashMap
        for (String key : webSocketSet.keySet()) {
            try {
                //判断接收用户是否是当前发消息的用户
                if (!userId.equals(key)) {
                    webSocketSet.get(key).sendMessage("用户:" + userId + "发来消息：" + " <br/> " + sendMessage);
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
