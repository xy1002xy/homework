package com.huyibo.springlogstash;


import com.huyibo.springcommon.config.LogAspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

/**
 * Created by huyibo on 2020/2/11.
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@Import(LogAspect.class)
public class SlsApplication {

    public static void main(String[] args){
        SpringApplication.run(SlsApplication.class);
    }
}
