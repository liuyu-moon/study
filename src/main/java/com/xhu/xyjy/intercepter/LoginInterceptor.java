package com.xhu.xyjy.intercepter;


import com.xhu.xyjy.dao.UserMapper;
import com.xhu.xyjy.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserMapper userMapper;
    /**
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return true:放行 false：拦截
     * @throws Exception
     */

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //获取session对象
        HttpSession session = httpServletRequest.getSession();

        System.out.println("执行拦截器");
        //判断session中是否保存了登陆的用户信息
        User user=(User)session.getAttribute("user");
        if (user==null){
            //session中没有对应的用户信息，重定向到登陆页面
            String contextPath = httpServletRequest.getContextPath();
            httpServletResponse.sendRedirect(contextPath+"/goLogin");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
