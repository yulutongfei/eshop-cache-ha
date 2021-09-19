package com.roncoo.eshop.cache.ha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 孙许
 * @version 1.0
 * @date 2021/9/19 16:37
 */
@Controller
public class HelloController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(String name) {
        return "hello, " + name;
    }
}
