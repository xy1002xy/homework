package com.huyibo.springgateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: spring-cloud-demo
 * @description: 数据拦截配置
 * @author: wxy
 * @create: 2020-03-13 21:58
 **/
@Component
@Data
@ConfigurationProperties("filter.global")
public class DataFilterConfig {

    private List<String> authPath;

    private List<String> userLoginPath;
}
