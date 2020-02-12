package com.huyibo.springlogstash.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by huyibo on 2020/2/11.
 */
@Controller
@Slf4j
public class LogTestController {

    @RequestMapping("/test1")
    @ResponseBody
    public String test(){
        log.info("你好啊e");
        log.warn("This is a warn message!");
        log.error("This is error message!");
        return "server被调用了！:";
    }
}
