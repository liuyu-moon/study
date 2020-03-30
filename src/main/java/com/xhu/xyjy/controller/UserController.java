package com.xhu.xyjy.controller;


import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.xhu.xyjy.dao.FriendMapper;
import com.xhu.xyjy.dto.ResultData;
import com.xhu.xyjy.pojo.*;
import com.xhu.xyjy.service.FriendService;
import com.xhu.xyjy.service.UserService;
import org.apache.catalina.Session;
import org.apache.ibatis.annotations.Result;
import org.hibernate.validator.constraints.EAN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

//    @RequestMapping("user/list")
//    public List<User> listAlluser(Model model,
//                                  String username,
//                                  @RequestParam(defaultValue = "1") Integer page,
//                                  @RequestParam(defaultValue = "5") Integer pageSize){
//        if (username==null||username.equals("null")){
//            username="";
//        }
//        model.addAttribute("username",username);
//        PageInfo<User> pageInfo=userService.listAllUser(username,page,pageSize);
//        model.addAttribute("users",pageInfo.getList());
//        model.addAttribute("pageInfo",pageInfo);
//        return userService.listAll();
//    }
//
//        @RequestMapping("/")
//    public String index(){
//        return ("index");
//    }
//
//    //去登陆页面
//    @RequestMapping("/goLogin")
//    public  String gologin(){
//        return "login";
//    }
//
//    @RequestMapping("/login")
//    @ResponseBody
//    public ResultData login(int  user_id, String user_pwd, HttpServletRequest httpServletRequest)
//    {
//
//        System.out.println("aaaaa"+user_id+user_pwd);
//      if(userService.findById(user_id)!=null){
//          User user= userService.findById(user_id);
//          System.out.println("aaaaaaaqq11"+user.toString());
//          if (user_pwd.equals(user.getUser_pwd())){
//              HttpSession session=httpServletRequest.getSession();
//              session.setAttribute("user" ,user);
//              return new ResultData(200,"登陆成功");
//          }
//          else return  new ResultData(400,"用户密码错误");
//      }
//      else return  new ResultData(401,"用户账户错误");
//
//    }

    @RequestMapping("/gologin")
    public  String goLogin(){ return "login"; }

    @RequestMapping("/login")
    @ResponseBody
    public  ResultData login(User user, HttpServletRequest request,Model model){
        ResultData resultData=userService.findUserById(user);

        if (resultData.getCode()==200){
            HttpSession session=request.getSession();
            session.setAttribute("userId" ,resultData.getData2());
            session.setAttribute("loginer" ,resultData.getData());
        }
        return  resultData;


    }


    @RequestMapping("/goregister")//去注册页面

    public String goregister(){
        return  "register";
    }
    @RequestMapping("/register")
    @ResponseBody
    public ResultData register(User user){
        System.out.println(user.toString());
        ResultData resultData=new ResultData();
         resultData=userService.saveUser(user);
        System.out.println(resultData.getMsg());
        return  resultData;
        }

        //查看用户信息
    @RequestMapping("/gouserinfo/{action}")
    public String goUserInfo( @PathVariable(name = "action") int user_id,Model model){
        model.addAttribute("userInfo",userService.findUserInfo(user_id));
        return "userInfo";
    }

    @RequestMapping("/updateuserinfo/{action}")
    public String updateuserinfo( @PathVariable(name = "action") int user_id,Model model){
        model.addAttribute("user_id",user_id);
        return "updateUser";
    }



}