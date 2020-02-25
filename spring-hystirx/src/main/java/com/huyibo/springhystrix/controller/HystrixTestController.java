package com.huyibo.springhystrix.controller;

import com.huyibo.springhystrix.compoment.ResultVO;
import com.huyibo.springhystrix.service.SourceService;
import com.huyibo.springhystrix.service.impl.SourceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by huyibo on 2020/2/25.
 */
@Controller
public class HystrixTestController {

    @Autowired
    private SourceService sourceService;


    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    @ResponseBody
    public ResultVO test(String id) {
        return sourceService.source(id);
    }

}
