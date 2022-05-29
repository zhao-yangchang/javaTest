package com.zhaoyc.springbootinitial.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestController
 * @Description test 前端控制器
 * @Author zhaoyangchang
 * @Date 2022/5/28 下午9:23
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello world";
    }

}
