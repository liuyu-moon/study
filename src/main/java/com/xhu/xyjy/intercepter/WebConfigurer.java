package com.xhu.xyjy.intercepter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

////；拦截器配置文件
////@Configuration
////public class WebConfigurer implements WebMvcConfigurer {
////    @Override
////    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
////        interceptorRegistry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/goLogin","/login","/");
////
////    }
////}