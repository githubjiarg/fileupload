package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  接口信息管理
 * @author jiaruiguo
 */
@RestController
@RequestMapping("server")
public class ServerController {

    @RequestMapping("sayHello")
    public String sayHello(){
        return "hello spring boot !";
    }
}
