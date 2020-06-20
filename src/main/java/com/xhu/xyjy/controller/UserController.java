package com.xhu.xyjy.controller;



import com.xhu.xyjy.dto.ResultData;
import com.xhu.xyjy.pojo.*;
import com.xhu.xyjy.service.ChatService;

import com.xhu.xyjy.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    ChatService chatService;

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

        //查找用户信息
        ResultData resultData=userService.findUserById(user);
        if(resultData.getData()==null)
        {
          resultData.setMsg("用户名不存在");
          return  resultData;
        }
        //

        int userId=(Integer) resultData.getData2();

        int unread=userService.findUnread(userId);
        if (resultData.getCode()==200){
            HttpSession session=request.getSession();
            session.setAttribute("userId" ,resultData.getData2());
            session.setAttribute("loginer" ,resultData.getData());
            session.setAttribute("unread" ,unread);
        }
        return  resultData;


    }


    @RequestMapping("/goregister")//去注册页面
    public String goregister(){
        return "register.html";
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
    public String goUserInfo( @PathVariable(name = "action") int user_id,Model model,HttpServletRequest request){


        HttpSession session= request.getSession();
        String s=session.getAttribute("userId").toString();
        int userid= Integer.parseInt(s);

        if(user_id!=userid){
            userService.addRecord(userid,user_id);
        }


        model.addAttribute("userInfo",userService.findUserInfo(user_id));
        model.addAttribute("student",userService.findStudent(user_id));
        return "userInfo";
    }

    @RequestMapping("/updatestudent/{action}")
    public String updatestudent( @PathVariable(name = "action") int user_id,Model model){
        model.addAttribute("student",userService.findStudent(user_id));
        return "updateStudent";
    }

    @RequestMapping("/updatestudent")
    @ResponseBody
    public ResultData updatestudent(Student student,MultipartFile file[]){
        System.out.println("xss");
        return userService.updateStudent(student,file);

    }



    @RequestMapping("/updateuserinfo/{action}")
    public String updateuserinfo( @PathVariable(name = "action") int user_id,Model model){
        model.addAttribute("user_id",user_id);
//        return "updateUser";
        return "register2";
    }


    @RequestMapping("/updateuser")
    @ResponseBody
    public ResultData  updateuser(User user){

        ResultData resultData=new ResultData();
        resultData= userService.update(user);
        return  resultData;

    }

    @RequestMapping("/updatepic")
    @ResponseBody
    public ResultData  updatepic(int user_id,MultipartFile file[]){

        ResultData resultData=new ResultData();
        resultData= userService.updatepic(user_id,file);
        return  resultData;

    }



}
