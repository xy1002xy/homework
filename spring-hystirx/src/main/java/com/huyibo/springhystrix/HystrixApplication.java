package com.huyibo.springhystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * Created by huyibo on 2020/2/25.
 */
@SpringBootApplication
@EnableHystrix
public class HystrixApplication {

    public static void main(String[] args){
        SpringApplication.run(HystrixApplication.class);

    }

}
