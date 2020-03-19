package com.huyibo.springgateway.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: spring-cloud-demo
 * @description: 返回值封装
 * @author: wxy
 * @create: 2020-03-13 21:52
 **/
@Data
public class ResultModel<T> implements Serializable {
    /**
     * 返回错误码
     */
    private int code;

    /**
     * 返回错误信息
     */
    private String message;

    /**
     * 数据
     */
    private T data;

}
