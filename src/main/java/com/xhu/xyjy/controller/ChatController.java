package com.xhu.xyjy.controller;
import com.github.pagehelper.PageInfo;
import com.xhu.xyjy.dto.ChatUser;
import com.xhu.xyjy.dto.MessageUser;
import com.xhu.xyjy.dto.MomentUser;
import com.xhu.xyjy.pojo.ChatList;
import com.xhu.xyjy.pojo.Message;
import com.xhu.xyjy.service.ChatService;
import com.xhu.xyjy.websocket.ProductWebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ChatController {

    @Autowired
    ChatService chatService;
    @Autowired
    ProductWebSocket productWebSocket;

    @ResponseBody
    @GetMapping("test")
    public String test(String userId, String message) throws Exception {
        if (userId == "" || userId == null) {
            return "发送用户id不能为空";
        }
        if (message == "" || message == null) {
            return "发送信息不能为空";
        }
        productWebSocket.systemSendToUser(userId, message);
        return "发送成功！";
    }

    @RequestMapping(value = "/gows/{friendid}")
    public String ws(@PathVariable(name = "friendid") int friendid, Model model, HttpServletRequest request,
                     @RequestParam(defaultValue = "1") Integer page,
                     @RequestParam(defaultValue = "5") Integer pageSize) {
        HttpSession session= request.getSession();
        String s=session.getAttribute("userId").toString();
        int userid= Integer.parseInt(s);

        chatService.findChat(userid,friendid);
        //查找历史消息
        PageInfo<MessageUser> pageInfoMsg=chatService.findHistoryMsg(userid,friendid,page,pageSize);

        PageInfo<ChatUser> pageInfoChat= chatService.findChatList(userid,page,pageSize);
        //加入聊天表
        model.addAttribute("chatlists",pageInfoChat.getList());
        model.addAttribute("pageInfoChat",pageInfoChat);
        //加入历史消息
        model.addAttribute("messages",pageInfoMsg.getList());
        model.addAttribute("pageInfoMsg",pageInfoMsg);
        //
        model.addAttribute("friendid",friendid);
        model.addAttribute("userid",userid);
        return "ws";
    }

    @RequestMapping(value = "/ws1")
    public String ws1() {
        return "ws1";
    }

    @RequestMapping(value = "/gomsg")
    public String gomsg() {
        return "message";
    }
}