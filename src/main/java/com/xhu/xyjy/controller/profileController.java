package com.xhu.xyjy.controller;

import com.github.pagehelper.PageInfo;
import com.xhu.xyjy.dto.MomentUser;
import com.xhu.xyjy.pojo.Moment;
import com.xhu.xyjy.service.MomentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class profileController {

    @Autowired
    MomentService momentService;


    @RequestMapping("/profile/{action}")
    public  String Profile(@PathVariable(name = "action") String action, Model model,
                           @RequestParam(defaultValue = "1") Integer page,
                           @RequestParam(defaultValue = "5") Integer pageSize){
        //从session获取到userID
        Integer user_id=1;

            if ("mymoment".equals(action)){
                System.out.println("我的动态");
                model.addAttribute("section","moment");
                model.addAttribute("sectionName","我的动态");
                PageInfo<Moment> pageInfo=momentService.selectById(user_id,page,pageSize);
                model.addAttribute("moments",pageInfo.getList());
                model.addAttribute("pageInfo",pageInfo);
            }
            if ("fcircle".equals(action)){
                System.out.println("朋友圈");
                model.addAttribute("section","moment");
                model.addAttribute("sectionName","朋友圈");
                PageInfo<MomentUser> pageInfo=momentService.selectFriendById(user_id,page,pageSize);
                model.addAttribute("moments",pageInfo.getList());
                model.addAttribute("pageInfo",pageInfo);
            }
            if ("focus".equals(action)){
                model.addAttribute("section","moment");
                model.addAttribute("sectionName","与我相关");
            }


        return "profile";
    }
}
