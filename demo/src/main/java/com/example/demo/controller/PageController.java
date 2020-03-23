package com.example.demo.controller;

import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.DefaultErrorViewResolver;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *  页面导航
 * @author jiaruiguo
 */
@Controller
@RequestMapping("/page")
public class PageController {

    DefaultErrorViewResolver defaultErrorViewResolver;

    @RequestMapping("/index")
    public String login(){
        return "index";
    }


}
