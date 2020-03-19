package com.huyibo.springgateway.utils;

import org.springframework.util.AntPathMatcher;

/**
 * @program: spring-cloud-demo
 * @description: 封装路径匹配方法
 * @author: wxy
 * @create: 2020-03-13 21:55
 **/
public class PathUtil {
    private static AntPathMatcher matcher = new AntPathMatcher();

    public static boolean isPathMatch(String pattern, String path) {
        return matcher.match(pattern, path);
    }
}
