package com.huyibo.springgateway.custom;

import com.netflix.loadbalancer.*;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;


/**
 * Created by huyibo on 2020/2/27.
 */
@Slf4j
public class CustomRobbinRule extends RandomRule {

    public AbstractServerPredicate getPredicate() {
        return null;
    }

    private int total = 0;            // 总共被调用的次数，目前要求每台被调用5次
    private int currentIndex = 0;    // 当前提供服务的机器号

    public Server choose(Object key) {
        RequestContext rctx = RequestContext.getCurrentContext();
        log.info(rctx.getRequest().getRequestURL().toString());
        return super.choose(key);
    }
}
