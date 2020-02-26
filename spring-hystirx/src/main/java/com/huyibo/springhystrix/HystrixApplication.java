package com.huyibo.springhystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Created by huyibo on 2020/2/25.
 */
@SpringBootApplication
@EnableHystrix
@EnableEurekaClient
@EnableFeignClients
public class HystrixApplication {

    public static void main(String[] args){
        SpringApplication.run(HystrixApplication.class);

    }

}
