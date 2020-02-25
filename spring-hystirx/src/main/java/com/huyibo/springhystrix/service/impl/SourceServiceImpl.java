package com.huyibo.springhystrix.service.impl;

import com.huyibo.springhystrix.compoment.ResultVO;
import com.huyibo.springhystrix.service.SourceService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * Created by huyibo on 2020/2/25.
 */
@Service
public class SourceServiceImpl implements SourceService {


    //    @HystrixCommand(fallbackMethod = "hiError",groupKey = "testGroup", threadPoolKey = "testThreadKey")
    //配置完cacheResult 之后 可以在cache 方法上放回结果，cacheKey 结果必须为String 当多次请求同一个key时，能够返回之前的查询
    //感觉实际生产中 如果已经有了fallback 接口 则接下来未必是需要cache 而是通过手动降级去实现
    @CacheResult(cacheKeyMethod = "hiGetCache")
    @HystrixCommand(fallbackMethod = "hiError", groupKey = "testGroup", threadPoolKey = "testThreadKey")
    public ResultVO source(String id) {
        if (StringUtils.isBlank(id)) {
            throw new RuntimeException("consumer exception");
        }
        return new ResultVO(1, "good");
    }

    public ResultVO hiError(String id) {
        String message = "hi error";
        System.out.println(message);
        return new ResultVO(3, "no!!");
    }

    @HystrixCommand(fallbackMethod = "hiError")
    public String hiGetCache(@CacheKey(value = "id") String id) {
        return " hiGetCache";
    }
}
