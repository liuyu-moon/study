package com.xhu.xyjy.controller;

import com.github.pagehelper.PageInfo;
import com.xhu.xyjy.dao.UserMapper;
import com.xhu.xyjy.dto.FriendInfo;
import com.xhu.xyjy.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class friendController {
    @Autowired
    FriendService friendService;
    @Autowired
    UserMapper userMapper;
    @RequestMapping("/gofriendlist")
    public  String Friend(Model model, HttpServletRequest request,
                          @RequestParam(defaultValue = "1") Integer page,
                          @RequestParam(defaultValue = "5") Integer pageSize){
        HttpSession session=request.getSession();
        String s=session.getAttribute("userId").toString();
        int user_id= Integer.parseInt(s);
        System.out.println("userid"+user_id);
        PageInfo<FriendInfo> pageInfo= friendService.findFriendList(user_id,page,pageSize);
        model.addAttribute("friendInfos",pageInfo.getList());
        model.addAttribute("pageInfo",pageInfo);
        return "friendlist";
    }

    @RequestMapping("/searchfriend/{user_name}")
    public  String searchFriend(@PathVariable(name = "user_name") String user_name, Model model, HttpServletRequest request,
                                @RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "5") Integer pageSize){
        HttpSession session=request.getSession();
        String s=session.getAttribute("userId").toString();
        int user_id= Integer.parseInt(s);
        System.out.println("userid"+user_id);
        PageInfo<FriendInfo> pageInfo= friendService.findFriendByName(user_id,user_name,page,pageSize);
        model.addAttribute("friendInfos",pageInfo.getList());
        model.addAttribute("pageInfo",pageInfo);
        return "friendlist1";
    }




}
