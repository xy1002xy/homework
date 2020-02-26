package com.huyibo.springgateway;

import com.huyibo.springgateway.filter.GrayReleaseFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

/**
 * Created by huyibo on 2020/2/26.
 */
@SpringBootApplication
@EnableZuulProxy
@EnableFeignClients
@EnableEurekaClient
@Import(GrayReleaseFilter.class)
public class ZuulApplication {

    public static void main(String[] args){
        SpringApplication.run(ZuulApplication.class);
    }

}
