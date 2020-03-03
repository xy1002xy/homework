package com.huyibo.springgateway.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jmnarloch.spring.cloud.ribbon.support.RibbonFilterContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;


import java.util.Random;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * Created by huyibo on 2020/2/26.
 */
@Configuration
@Slf4j
public class GrayReleaseFilter extends ZuulFilter {

    public String filterType() {
        return PRE_TYPE;
    }

    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER - 1;
    }

    /**
     * TODO 可以改动态配置啊 + 路由表
     *
     * @return
     */
    public boolean shouldFilter() {
        return true;
    }

    public Object run() throws ZuulException {


        //todo 请求参数粒度 路由配置
        RequestContext rctx = RequestContext.getCurrentContext();
        log.info("=============== filter 初始化 ================");
        //todo 某个条件下 权重配比 这样搞搞
        if (true) {
            Random random = new Random();
//            int seed = random.nextInt() * 100;
//            if (seed <= 50) {
            RibbonFilterContextHolder.getCurrentContext().add("tag", "pro");
//            }
        }
        return null;
    }
}
