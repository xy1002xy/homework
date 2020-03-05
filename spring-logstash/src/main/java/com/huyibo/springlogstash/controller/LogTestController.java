package com.huyibo.springlogstash.controller;

import com.huyibo.springlogstash.dao.DaoDemoService;
import com.huyibo.springlogstash.feignsinvoke.Feignservice;
import com.huyibo.springlogstash.vo.TestVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private Feignservice feignservice;

    @Autowired
    private DaoDemoService daoDemoService;


    private static final String test = "测试字节测试字节测试字节测试字节测试字节测试字节测试字节测试字节测试字节测试字节测试字节测试字节测试字节测试字节测试字节测试字节测试字节测试字节测试字节测试字节";

    @RequestMapping("/test1")
    @ResponseBody
    public String test1(){
        log.info("你好啊||测试字节"+test);
        log.error("This is error message!",new NullPointerException("这里有空指针"));
//        log.warn("This is a warn message!");
//        log.error("This is error message!");
        return "server被调用了！:";
    }

    @RequestMapping("/test2")
    @ResponseBody
    public String test2(){
        log.info("你好啊e");
        log.warn("This is a warn message!");
        log.error("This is error message!",new NullPointerException("这里有空指针"));
        return feignservice.sayHiFromClientOne("1");
    }

    @RequestMapping("/test3")
    @ResponseBody
    public String test3(){
        TestVO testVO = new TestVO();
        testVO.setAge(11);
        testVO.setName("abcdefg");
        return daoDemoService.debuInfoTest(testVO);
    }
}
