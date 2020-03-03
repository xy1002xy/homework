package com.huyibo.springgateway.config;

import com.huyibo.springgateway.custom.CustomRobbinRule;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.WeightedResponseTimeRule;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Created by huyibo on 2020/2/28.
 */
@Configuration
@Slf4j
public class MySelfRule {

    @Bean
    @Primary
    public IRule myRule(){
        //todo 请求参数粒度 路由配置
        log.info("=============== myRule 初始化 ================");
        return new CustomRobbinRule();
    }

}
