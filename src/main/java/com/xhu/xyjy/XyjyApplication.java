package com.xhu.xyjy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;



@MapperScan(basePackages = "com.xhu.xyjy.dao")
@ComponentScan(basePackages = {"com.xhu.xyjy.controller","com.xhu.xyjy.service","com.xhu.xyjy.intercepter","com.xhu.xyjy.websocket"})
@SpringBootApplication
public class XyjyApplication {

    public static void main(String[] args) {
        SpringApplication.run(XyjyApplication.class, args);
    }

}
