package com.huyibo.springgateway.filter;

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

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER - 1;
    }

    /**
     * TODO 可以改动态配置啊 + 路由表
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {


        //todo 请求参数粒度 路由配置
        RequestContext rctx = RequestContext.getCurrentContext();
        rctx.getRequest();
        log.info("=============== filter 初始化 ================");
        //todo 某个条件下 权重配比 这样搞搞
        if (true) {
            Random random = new Random();
            int seed = random.nextInt() * 100;
            if (seed <= 10) {
            RibbonFilterContextHolder.getCurrentContext().add("tag", "VERSION1");
            }
        }
        return null;
    }
}
