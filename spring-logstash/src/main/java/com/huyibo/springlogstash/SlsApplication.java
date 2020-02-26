package com.huyibo.springlogstash;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Created by huyibo on 2020/2/11.
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class SlsApplication {

    public static void main(String[] args){
        SpringApplication.run(SlsApplication.class);
    }
}
