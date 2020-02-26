package com.huyibo.springlogstash.feignsinvoke;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by huyibo on 2020/2/26.
 */
//@FeignClient(value = "client-test",fallback = SchedualServiceHiHystric.class)
@FeignClient(value = "spring-hystrix")
@Service
public interface Feignservice {

    @RequestMapping(value = "/test1",method = RequestMethod.GET)
    String sayHiFromClientOne(@RequestParam("id") String id);

}
