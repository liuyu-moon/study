package com.xhu.xyjy.controller;

import com.github.pagehelper.PageInfo;
import com.xhu.xyjy.dao.UserMapper;
import com.xhu.xyjy.dto.FriendInfo;
import com.xhu.xyjy.dto.ResultData;
import com.xhu.xyjy.pojo.User;
import com.xhu.xyjy.service.FriendService;
import com.xhu.xyjy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;

@Controller
public class friendController {
    @Autowired
    FriendService friendService;
    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    @RequestMapping("/gofriendlist")
    public  String Friend(Model model, HttpServletRequest request,
                          @RequestParam(defaultValue = "1") Integer page,
                          @RequestParam(defaultValue = "5") Integer pageSize){
        HttpSession session=request.getSession();
        String s=session.getAttribute("userId").toString();
        int user_id= Integer.parseInt(s);
        System.out.println("userid"+user_id);

        List<User> a=friendService.getAdviceF(user_id);
        //附近的人
        List<User> b=userService.findNearby(user_id);

        //感兴趣的人

        List<User> f=userService.findInterest(user_id);

        model.addAttribute("advice",a);
        model.addAttribute("nearby",b);
        model.addAttribute("interest",f);

        PageInfo<FriendInfo> pageInfo= friendService.findFriendList(user_id,page,pageSize);
        model.addAttribute("friendInfos",pageInfo.getList());
        model.addAttribute("friendInfosSize",pageInfo.getList().size());
        model.addAttribute("pageInfo",pageInfo);
        return "friendlist";
    }

    @RequestMapping("/searchFriend/{user_name}")
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
        model.addAttribute("friendInfosSize",pageInfo.getList().size());
        return "friendlist";
    }

    @RequestMapping("/addFriend")
    @ResponseBody
    public ResultData addFriend(int user_id,int friend_id,int type){
        if( friendService.isFreind(user_id,friend_id)!=0){
            return  new ResultData(2002,"对方已经是你的好友");
        }
        return friendService.addFriend(user_id,friend_id,type);
    }

    @RequestMapping("/groupfriend")
    @ResponseBody
    public ResultData groupFriend(int type,HttpServletRequest request)
    {
        HttpSession session=request.getSession();
        String s=session.getAttribute("userId").toString();
        int user_id= Integer.parseInt(s);
       List<User> groupFriend=friendService.groupFriend(user_id,type);
       ResultData resultData=new ResultData(200,"组内好友",groupFriend,groupFriend.size());

       return  resultData;
    }

}
