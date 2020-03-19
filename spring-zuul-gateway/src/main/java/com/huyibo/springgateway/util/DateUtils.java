package com.huyibo.springgateway.util;

import javax.xml.crypto.Data;
import java.util.Calendar;
import java.util.Date;

/**
 * @program: spring-cloud-demo
 * @description: 时间工具类
 * @author: wxy
 * @create: 2020-03-14 00:16
 **/
public class DateUtils {


    public static Date getAfterDays(int days) {
        //得到日历
        Calendar calendar = Calendar.getInstance();
        //把当前时间赋给日历
        calendar.setTime(new Date());
        //设置为7天前
        calendar.add(Calendar.DAY_OF_MONTH, +days);
        //得到7天前的时间
        Date resultDays = calendar.getTime();
        return resultDays;
    }
}
